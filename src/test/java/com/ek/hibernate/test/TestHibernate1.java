package com.ek.hibernate.test;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;
/**
 * 对象存在内存中 , 数据库中没有 , 瞬时状态
 * 对象存在并与数据库形成一一对应关系 , 并且session开启 --> 持久状态
 * 对象存在并与数据库形成一一对应关系 , 并且session关闭 --> 脱管状态
 */ 
public class TestHibernate1 {
	
	private Session session = null;
	
	@Before //这表示的是 可以提前完成的 在test执行之前可以先执行这里 加载一些资源
	public void init(){
		session = HibernateSessionFactory.getSession(); //获得hibernate session 工厂
	}
	
	@After
	public void close(){
		HibernateSessionFactory.closeSession(); //关闭hibernate session 工厂
	}
	
	
	//测试新增 save
	@Test 
	public void testAddTestUserInfo1() {
		TestUserInfo user = new TestUserInfo();
		user.setUsername("ek");
		user.setSex(true);
		user.setBirthday(new Date());
		
		Transaction tr = null; 
	
		try {
			tr = session.beginTransaction(); //获得hibernate 事务
			session.save(user); //保存
			tr.commit(); //提交事务
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback(); //回滚事务
			}
			e.printStackTrace();
		} 
	}
	
	
	
	
	//测试新增 saveOrUpdate
	@Test 
	public void testAddTestUserInfo2() {
		TestUserInfo user = new TestUserInfo();
		user.setUsername("etttk");
		user.setSex(false);
		user.setBirthday(new Date());
		
		Transaction tr = null; 
	
		try {
			tr = session.beginTransaction(); //获得hibernate 事务
			session.saveOrUpdate(user); //默认:有id修改,没有id保存 , 如果设置<id unsaved-value="" 由这个参数决定是save还是update 如果有version由version决定
			tr.commit(); //提交事务
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback(); //回滚事务
			}
			e.printStackTrace();
		} 
	}
	
	
	
	
	//测试update修改
	@Test
	public void testUpdateTestUserInfo1(){
		TestUserInfo user = new TestUserInfo();
		user.setUuid(17);
		user.setUsername("mager");
		user.setSex(true);
		user.setBirthday(new Date());
	 
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.update(user); //修改一条数据
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	//测试同步
	@Test
	public void testUpdateTestUserInfo2(){
		TestUserInfo user = new TestUserInfo();
		user.setUuid(17);
		user.setUsername("mager");
		user.setSex(true);
		user.setBirthday(new Date());
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			TestUserInfo oldUser = (TestUserInfo)session.get(TestUserInfo.class , 2);
			if(oldUser!=null){
				oldUser.setUsername("lahehe"); //这没有执行修改,但是因为oldUser是持久状态,这里改变值会同步到数据库
			}
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		} 
	}
	
	
	//测试merge修改
	@Test
	public void testUpdateTestUserInfo3(){
		TestUserInfo user = new TestUserInfo();
		user.setUuid(27);
		user.setUsername("mager");
		user.setSex(false);
		user.setBirthday(new Date());
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.merge(user); //合并 , 没有就新增,有就修改
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		} 
	}
	
	
	//测试删除
	@Test
	public void testDeleteTestUserInfo(){
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			TestUserInfo oldUser = (TestUserInfo)session.get(TestUserInfo.class , 27);
			if(oldUser!=null){
				session.delete(oldUser); //删除
			}
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		} 
	}
	
	
	
	//测试get查询(立即加载)
	@Test
	public void testGetTestUserInfo(){
		Transaction tr = null; 
		TestUserInfo user = null;
		
		try {
			tr = session.beginTransaction();
			user = (TestUserInfo) session.get(TestUserInfo.class , 1);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		} 
		System.out.println(user.getUsername());
	}
	
	
	// 测试load查询(懒惰/延时加载)load如果在session关闭后 , 对象在托管状态下 是不会去取值的
	// 但是在pojo配置中 设置了 <class lazy="false" 非懒惰   lazy="true" 懒惰
	@Test
	public void testLoadTestUserInfo(){
		Transaction tr = null; 
		TestUserInfo user = null;
		
		try {
			tr = session.beginTransaction();
			user = (TestUserInfo)session.load(TestUserInfo.class , 1);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		} 
//		finally{
//			HibernateSessionFactory.closeSession();
//		}
		//首先要知道 @After中的代码实在 走完这个@Test中的代码 , 最后才会去走的
		//如果session 在@After中关闭 , 走到这里session还是开启的 , 所以这时user是持久
		//如果session 在 finally 中关闭 , 走到这里session已经关闭的 , 所以这时user是脱管
		System.out.println(user.getUsername());//这一句会触发select , 但如果session已经关闭了就没有值
	}
	
	
	
	
	//衍生属性 <property name="age"> <formula>( YEAR(SYSDATE())-YEAR(birthday) )</formula> </property>
	@Test
	public void getUserAge(){
		Transaction tr = null; 
		TestUserInfo user = null;
		try {
			tr = session.beginTransaction();
			user = (TestUserInfo)session.get(TestUserInfo.class, 1);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
		System.out.println(user.getAge());
	}
}

