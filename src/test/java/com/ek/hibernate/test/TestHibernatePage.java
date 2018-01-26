package com.ek.hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings("unchecked")
public class TestHibernatePage {
	// hibernate 分页
	private Session session = null;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	// Hibernate 自带支持分页
	
	@Test
	public void testPage1(){
		Query query = session.createQuery("from TestUserInfo");

		query.setFirstResult(0); //记录查询开始的行号
		query.setMaxResults(2); //每页记录数
		
		List<TestUserInfo> list = query.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	@Test
	public void testPage2(){
		Criteria criteria = session.createCriteria(TestUserInfo.class);
		
		criteria.setFirstResult(0); //记录查询开始的行号
		criteria.setMaxResults(2); //每页记录数
		
		List<TestUserInfo> list = criteria.list();
		for (TestUserInfo userInfo : list) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	@Test
	public void testPage3(){
		SQLQuery sqlQuery =session.createSQLQuery(" select * from test_user_info ");
		
		sqlQuery.setFirstResult(2);
		sqlQuery.setMaxResults(2);
		
		List<Object[]> list = sqlQuery.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
	}
}