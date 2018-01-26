package com.ek.hibernate.test4;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.interceptor.AuditInterceptor;
import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;

/**
 *拦截器 可以拦截做了什么操作 比如增删改
 */
public class TestHibernate_Interceptor {
private Session session;
	
	@Before
	public void initializeSession(){
		//在这里使用拦截器
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		session = sessionFactory.openSession(new AuditInterceptor());
	}
	
	@After
	public void close(){
		session.close();
	}
	
	
	
	@Test
	public void testOnSave(){
		TestUserInfo user = new TestUserInfo();
		user.setUsername("hahah");
		user.setSex(true);
		user.setAge(12);
		user.setBirthday(new Date());
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.save(user);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
}