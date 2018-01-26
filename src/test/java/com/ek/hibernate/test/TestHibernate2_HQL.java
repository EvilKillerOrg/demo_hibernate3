package com.ek.hibernate.test;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo1.TestUserInfo;
import com.ek.hibernate.model.util.HibernateSessionFactory;
@SuppressWarnings("unchecked")
public class TestHibernate2_HQL {
	// Query HQL
	private Session session = null;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	
	@After
	public void closeSession(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	//查询所有
	@Test 
	public void findUserInfo1(){
		Query query = session.createQuery("from TestUserInfo"); //HQL
		List<TestUserInfo> userlist = query.list();
		for (TestUserInfo userInfo : userlist) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//查询所有
	@Test
	public void findUserInfo2(){
		Query query = session.createQuery("select u from TestUserInfo u"); 
		List<TestUserInfo> userlist = query.list();
		for (TestUserInfo userInfo : userlist) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//查询一个域
	@Test
	public void findUserInfo3(){
		Query query = session.createQuery("select u.username from TestUserInfo u"); 
		List<String> usernameList = query.list();
		 for (String username : usernameList) {
			System.out.println(username);
		}
	}
	
	
	
	//查询多个域 , 用Object封装
	@Test
	public void findUserInfo4(){
		Query query = session.createQuery("select u.username , u.birthday from TestUserInfo u"); 
		List<Object[]> list = query.list();
		 for (Object[] obj : list) {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
	}
	
	
	
	//查询多个域 , 用pojo封装  //pojo 中要有包含需要查询域的构造方法
	@Test
	public void findUserInfo5(){
		Query query = session.createQuery("select new TestUserInfo ( u.username , u.birthday) from TestUserInfo u"); 
		List<TestUserInfo> userInfoList = query.list();
		 for (TestUserInfo user : userInfoList) {
			System.out.println(user.getUsername());
			System.out.println(user.getBirthday());
		}
	}
	
	
	
	//查询多个域 , 用list集合封装
	@Test
	public void findUserInfo6(){
		Query query = session.createQuery("select new list ( u.username , u.birthday) from TestUserInfo u"); 
		List<List<Object>> userInfoList = query.list();
		 for (List<Object> list : userInfoList) {
			System.out.println(list.get(0));
			System.out.println(list.get(1));
		}
	}
	
	
	
	//查询多个域 , 用map集合封装 要加别名 作为map的key
	@Test
	public void findUserInfo7(){
		Query query = session.createQuery("select new map ( u.username  as name, u.birthday as birth ) from TestUserInfo u"); 
		List<Map<String , Object>> userInfoList = query.list();
		for (Map<String, Object> map : userInfoList) {
			System.out.println(map.get("name"));
			System.out.println(map.get("birth"));
		}
	}
		
		
		
	//条件查询
	@Test
	public void findUserInfo8(){
		//Query query = session.createQuery(" from TestUserInfo u where u.username like '%a%' "); 
		Query query = session.createQuery(" from TestUserInfo u where u.id = 1 "); 
		List<TestUserInfo> userInfoList = query.list();
		for (TestUserInfo userInfo : userInfoList) {
			System.out.println(userInfo.getUsername());
		}
	}	
		
	
	
	//条件查询
	@Test
	public void findUserInfo9(){
		Query query = session.createQuery(" from TestUserInfo u where u.id = ? "); 
		query.setInteger(0, 2);  // 前面是位置第几个?(不同的是预处理从1开始HQL从0开始) , 后面是值
		List<TestUserInfo> userInfoList = query.list();
		for (TestUserInfo userInfo : userInfoList) {
			System.out.println(userInfo.getUsername());
		}
	}	
	
	
	
	//条件查询
	@Test
	public void findUserInfo10(){
		Query query = session.createQuery(" from TestUserInfo u where u.id = :uuid "); 
		query.setInteger("uuid", 3);  //这不是按位置赋值 , 是按名称
		List<TestUserInfo> userInfoList = query.list();
		for (TestUserInfo userInfo : userInfoList) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//order by
	@Test
	public void findUserInfo11(){
		Query query = session.createQuery(" from TestUserInfo u order by username desc"); 
		List<TestUserInfo> userInfoList = query.list();
		for (TestUserInfo userInfo : userInfoList) {
			System.out.println(userInfo.getUsername());
		}
	}
	
	
	
	//group by  
	//在 hibernate.cfg.xml 设置 <property name="query.substitutions">true 1 , false 0</property> 替换 boolean
	@Test
	public void findUserInfo12(){
		Query query = session.createQuery(" select u.sex , count(u.sex) from TestUserInfo u group by sex "); 
		List<Object[]> userInfoList = query.list();
		for (Object[] obj : userInfoList) {
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
	}
		
		
		
	//实现HQL  <query 命名查询 (配置在 映射文件中的)
	@Test
	public void findUserInfo13(){
		Query query = session.getNamedQuery("HQLUserInfo");
		
		query.setString("username", "%a%");
		
		List<TestUserInfo> list = query.list();
		for (TestUserInfo user : list) {
			System.out.println(user.getUsername());
		}
	}
}