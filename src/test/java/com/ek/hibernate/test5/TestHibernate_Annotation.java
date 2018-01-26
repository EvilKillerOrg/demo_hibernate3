package com.ek.hibernate.test5;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ek.hibernate.model.pojo4.TestAnnotation;
import com.ek.hibernate.model.util.HibernateSessionFactoryAnnotation;

import junit.framework.TestCase;

public class TestHibernate_Annotation extends TestCase {

	
	public void testAnnotation(){
		TestAnnotation annot = new TestAnnotation();
		annot.setUsername("hhhh");
		annot.setSex(1);
		annot.setBirthday(new Date());
		
		 Session session = HibernateSessionFactoryAnnotation.getSession();
		 Transaction tr = null; 
		 
		 try {
			tr = session .beginTransaction();
			 session.save(annot);
			 tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}finally{
			HibernateSessionFactoryAnnotation.closeSession();
		}
		 
		 
	}
	 
}