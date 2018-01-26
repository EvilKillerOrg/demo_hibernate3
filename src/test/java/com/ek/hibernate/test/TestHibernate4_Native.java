package com.ek.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

// Native SQLQuery (原始的SQL , 特别复杂的时候才用, 一般就用Criteria查询)
@SuppressWarnings("unchecked")
public class TestHibernate4_Native {
	
	private Session session = null;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	// 这样取出来的是对象数组 不方便
	@Test
	public void testFindUserInfo1(){
		SQLQuery sqlQuery =session.createSQLQuery(" select * from test_user_info ");
		
		List<Object[]> list = sqlQuery.list();
		for (Object[] obj : list) {
			System.out.print(obj[0]);
			System.out.println(obj[1]);
		}
	}
	
	
	
	// 这样取出来的是 用户对象
	@Test
	public void testFindUserInfo2(){
		SQLQuery sqlQuery =session.createSQLQuery(" select {u.*} from test_user_info u ");
		
		sqlQuery.addEntity("u" , TestUserInfo.class); //告诉SQLQuery 对象类型是什么 (必须有别名, 别名要加{ })
		
		List<TestUserInfo> list = sqlQuery.list();
		for (TestUserInfo user : list) {
			System.out.println(user.getUsername());
		}
	}
	
	
	
	//实现native <sql-query 命名查询 (配置在 映射文件中的)
	@Test
	public void testFindUserInfo3(){
		Query query = session.getNamedQuery("selectUser");
			
		List<TestUserInfo> list = query.list();
		for (TestUserInfo user : list) {
			System.out.println(user.getUsername());
		}
	}
		
		
		
	//实现native <sql-query 命名查询 (配置在 映射文件中的)
	@Test
	public void testFindUserInfo4(){
		Query query = session.getNamedQuery("selectUserInfo");
			
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
	}
	
	
}