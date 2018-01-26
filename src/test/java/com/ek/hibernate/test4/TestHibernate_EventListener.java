package com.ek.hibernate.test4;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

/**
 * 事件 (监听器) 监听器类配置文件还要配
 */
public class TestHibernate_EventListener {
	private Session session;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	@After
	public void close(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	@Test
	public void testLoadEventListener(){
		try {
			TestUserInfo user = (TestUserInfo)session.load(TestUserInfo.class, 1);
			System.out.println(user);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}