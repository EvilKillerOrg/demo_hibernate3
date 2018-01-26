package com.ek.hibernate.test2;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo2.ZLesson;
import com.ek.hibernate.model.pojo2.ZStudent;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings("unchecked")
public class ManyToMany {

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
	
	
	
	@Test //通过student级联lesson(lesson的映射文件 inverse="false")  通过lesson级联student (student的映射文件 inverse="false")
	public void testAdd(){ 
		ZStudent student = new ZStudent();
		student.setStName("eekk");
		student.setStSex("1");
		
		ZLesson lesson = new ZLesson();
		lesson.setLeName("自然");
		lesson.setHours(2);
		
		student.getzLesson().add(lesson);
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.save(student);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	@Test //级联把 student和lesson 关联关系 加到中间表
	public void testUpdate1(){
		Integer stUUID = 1;
		Integer leUUID= 3;
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			
			ZStudent student = (ZStudent)session.load(ZStudent.class, stUUID);
			ZLesson lesson = (ZLesson)session.load(ZLesson.class, leUUID);
			
			if(student!=null&&lesson!=null){
				//lesson.getzStudent().add(student); //通过lesson级联student 持久同步需要lesson映射文件inverse="false"
				student.getzLesson().add(lesson); //通过student级联lesson 持久同步需要student映射文件inverse="false"
			}
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	@Test //在中间表 解除关联关系
	public void testUpdate2(){
		Integer stUUID = 1;
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			
			ZStudent student = (ZStudent)session.load(ZStudent.class, stUUID);
			
			if(student!=null){
				student.getzLesson().clear(); //把student的lesson clear 同步到数据库 这样就解除了关联关系(中间表删除这个学生所有的课程)
			}
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	@Test //在中间表 解除关联关系 中间表删除这个学生的一门课程
	public void testUpdate3(){
		Integer stUUID = 1;
		Integer leUUID= 5;
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			
			ZStudent st = (ZStudent)session.load(ZStudent.class, stUUID);
			Set<ZLesson> lessons = st.getzLesson();
				
			ZLesson temp = null;
			for (ZLesson lesson : lessons) {
				if(lesson.getUuid().intValue()==leUUID.intValue()){  //UUID 都是Integer的对象类型 用== 比较不出来 intValue()转换成int
					temp = lesson;
				}
			}
			if(temp!=null){
				lessons.remove(temp);
			}
				
//  这样一边迭代,一边remove集合中的元素 这样的代码不能写!!!!!!!!!!!!!!!!! 真确的实现是上面的 弄个临时对象 把迭代的传进去 在迭代外面remove
//				for (ZLesson lesson : lessons) {
//					if(le.getUuid()==leUUID){
//						lessons.remove(lesson);
//					}
//				}
			
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
}