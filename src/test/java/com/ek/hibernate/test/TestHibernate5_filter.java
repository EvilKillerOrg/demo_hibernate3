package com.ek.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;


// filter
@SuppressWarnings("unchecked")
public class TestHibernate5_filter {
	private Session session = null;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	//Filter 在配置文件里 配着
	@Test
	public void teseUserInfo(){
		Query querty =  session.createQuery("from TestUserInfo");
		
		session.enableFilter("filterUserName").setParameter("myFilterParam", "%l%");
		
		List<TestUserInfo> list = querty.list();
		for (TestUserInfo user : list) {
			System.out.println(user.getUsername());
		}
	}
}