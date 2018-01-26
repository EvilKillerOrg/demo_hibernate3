package com.ek.hibernate.test3;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

/**
 * 1级缓存在session中 1级缓存只能存储一条数据
 * 2级缓存在配置文件里配置 映射文件里配 <cache usage="read-only"/> (TestUserInfo.hbm.xml)
 */
public class TestHibernate_2stLevelCache {
	
	//测试一级缓存
	@Test
	public void testQuery1(){
		
		Session session = HibernateSessionFactory.getSession();
		
		try {
			//由于session中缓存的存在下面两次查询打印1条SQL语句
			//1级缓存只能存储一条数据 查LIST 就不行打印的是两条
			TestUserInfo user1 = (TestUserInfo)session.get(TestUserInfo.class, 1);
			TestUserInfo user2 = (TestUserInfo)session.get(TestUserInfo.class, 1);
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
	
	
	//测试二级缓存
	@Test
	public void testQuery2(){
		//为了验证是否调用了缓存 调用一次查询 关闭一次session 再获得一次session 再关闭一次 看打印几次SQL
		// 如果是2条 2级缓存就没用到 , 如果是1条 就是用到2级缓存了
		//2级缓存和session的缓存 是没有关系的 所以session 缓存关闭了 还是可以取得数据 (不是从session的缓存的取的)
		// 可以试试把 开启2级缓存的代码 注释掉试试 就是从session的缓存取了 但是session关闭了一次,所以打印了2条SQL
		Session session = HibernateSessionFactory.getSession();
		
		try {
			Query query1 = session.createQuery(" from TestUserInfo ");
			query1.setCacheable(true); //优先从缓存查
			List<TestUserInfo> list1 = query1.list();
			
		} catch (HibernateException e1) {
			e1.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
		session = HibernateSessionFactory.getSession();
		
		try {
			Query query2 = session.createQuery(" from TestUserInfo ");
			query2.setCacheable(true); //优先从缓存查
			List<TestUserInfo> list2 = query2.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
	
}