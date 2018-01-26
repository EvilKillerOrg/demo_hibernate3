package com.ek.hibernate.test3;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings("unchecked")
public class TestHibernate_1stLevelCache {

	private Session session;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	/**
	 * session 开启缓存是有效的 
	 * session 关闭缓存就失效了
	 */
	//测试缓存 
	@Test
	public void testGetUserInfo(){
		try {
			TestUserInfo user1 = (TestUserInfo)session.get(TestUserInfo.class, 1);
		//	session.evict(user1); //清除缓存
		//	session.clear(); //清除所有缓存
			TestUserInfo user2 = (TestUserInfo)session.get(TestUserInfo.class, 1);
			
			if(user1==user2){
				System.out.println(" no session.evict() , user1 same as user2 ");
			}else{
				System.out.println(" user1 and user2 are different ");
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//
	@Test
	public void testGetUserInfo2(){
		try {
			TestUserInfo user = (TestUserInfo)session.get(TestUserInfo.class, 1);
			user.setUsername("123");
			
			/**
			 * query 取数据库的所有数据,如果缓存中已有ID相同的数据,则返回缓存中的而不是数据库中查到的(这里数据库是ek 返回的是123)
			 */
			Query	query = session.createQuery("from TestUserInfo");
				List<TestUserInfo> list = query.list();
				for (TestUserInfo userInfo : list) {
					System.out.println(userInfo.getUsername());
				}
			
//			Iterator<TestUserInfo> iterate = query.iterate();
//			while(iterate.hasNext()){
//				iterate.next();
//			}
		 
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//iterate 是一条一条去和缓存中的ID 匹配的 效率稍差, 一般不用iterate
	@Test
	public void testGetUserInfo3(){
		try {
			TestUserInfo user = (TestUserInfo)session.get(TestUserInfo.class, 1);
			user.setUsername("123");
		 
			Query	query = session.createQuery("from TestUserInfo");
			
			Iterator<TestUserInfo> iterate = query.iterate();
			while(iterate.hasNext()){
				System.out.println(iterate.next().getUsername());
			}
		 
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}