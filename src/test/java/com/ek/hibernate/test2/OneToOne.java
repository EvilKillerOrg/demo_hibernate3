package com.ek.hibernate.test2;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo2.ZAddress;
import com.ek.hibernate.model.pojo2.ZStudent;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings("unchecked")
public class OneToOne {

	/**
	 * 级联需要关注的2个参数
	 * inverse 决定主键有谁来维护
	 * cascade 决定知否级联操作(默认是不级联的)
	 */
	
	private Session session = null;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	// 用了 两个 save 保存的
	@Test
	public void testSave1(){
		ZStudent student = new ZStudent();
		student.setStName("ek");
		student.setStSex("1");
		ZAddress address = new ZAddress();
		address.setStreet("22");
		address.setPostcode("12345");
		
		Transaction tr = null; 
		try {
			tr = session.beginTransaction();
			
			session.save(student); 
			session.save(address); //两个save 走完 这两个对象 都到达 持久状态
			student.setzAddress(address); //持久状态下 同步到数据库
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	// 级联 的 方式 保存数据 通过学生保存地址
	@Test
	public void testSave2(){
		ZStudent student = new ZStudent();
		student.setStName("kk");
		student.setStSex("1");
		ZAddress address = new ZAddress();
		address.setStreet("33");
		address.setPostcode("222");
		
		student.setzAddress(address); //级联 要在student的映射文件中射cascade
		
		Transaction tr = null; 
		try {
			tr = session.beginTransaction();
			
			session.save(student);  // 给student set了 address 直接保存student就会级联保存address
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	// 级联修改
	@Test
	public void testUpdate(){
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			
			ZStudent student = (ZStudent)session.get(ZStudent.class, 1);
			student.setStName("hehe"); //student是持久状态的 直接set就可以同步进去了
			if(student.getzAddress()!=null){
				student.getzAddress().setStreet("yyyyyy"); //级联修改
			}
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	// 级联删除
	@Test
	public void testDelete1(){
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			
			ZStudent student = (ZStudent)session.get(ZStudent.class, 5);
			if(student!=null){
				session.delete(student);
			}
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	// 级联删除
	@Test
	public void testDelete2(){
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			
			Query query = session.createQuery("delete from ZStudent where id=6"); //不能级联删除
			query.executeUpdate();
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	
	//查询
	@Test
	public void testQuery(){
		
		Query query = session.createQuery("from ZStudent where zAddress.street like '%z%' ");
		List<ZStudent> list = query.list();
		for (ZStudent student : list) {
			System.out.println(student.getStName());
		}
		
	}
}