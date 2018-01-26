package com.ek.hibernate.test4;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ek.hibernate.model.pojo3.Employee;
import com.ek.hibernate.model.pojo3.EmployeeCommon;
import com.ek.hibernate.model.pojo3.EmployeeManager;
import com.ek.hibernate.model.util.HibernateSessionFactory;

/**
 * POJO的继承 相关POJO  Employee, EmployeeCommon, EmployeeManager
 * 这种继承的逻辑 可以用在权限管理(比如角色分类很多种可以做成这种继承的)
 */
@SuppressWarnings("unchecked")
public class TestHibernate_Extend {

	private Session session;
	
	@Before
	public void initializeSession(){
		session = HibernateSessionFactory.getSession();
	}
	@After
	public void close(){
		HibernateSessionFactory.closeSession();
	}
	
	
	
	//这个就是员工的基本信息  没有isMarried 也没有 hasChild 要在Employee的配置文件中给鉴别器设置默认值
	//<class name="com.ek.hibernate.model.pojo3.Employee"   discriminator-value="none"  table="employee"  catalog="hibernate_db">
	@Test 
	public void testAddEmployee(){
		Employee employee = new Employee();
		employee.setUsername("kkkkeee");
		employee.setSex("1");
		employee.setBirthday(new Date());
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.save(employee);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	//鉴别器的值是:manager
	//<subclass name="com.ek.hibernate.model.pojo3.EmployeeManager" discriminator-value="manager">
	@Test 
	public void testAddEmployeeManager(){
		EmployeeManager employee = new EmployeeManager();
		employee.setUsername("rrrrr");
		employee.setSex("1");
		employee.setBirthday(new Date());
		employee.setHasChild("0");
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.save(employee);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	//鉴别器的值是:manager
	//<subclass name="com.ek.hibernate.model.pojo3.EmployeeManager" discriminator-value="manager">
	@Test 
	public void testAddEmployeeCommon(){
		EmployeeCommon employee = new EmployeeCommon();
		employee.setUsername("ttttt");
		employee.setSex("1");
		employee.setBirthday(new Date());
		employee.setIsMarried("1");
		
		Transaction tr = null; 
		
		try {
			tr = session.beginTransaction();
			session.save(employee);
			tr.commit();
		} catch (HibernateException e) {
			if(tr!=null){
				tr.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@Test
	public void testQuery1(){
		Query query = session.createQuery(" from Employee");
		List<Employee> list = query.list();
		for (Employee employee : list) {
			System.out.println(employee.getUsername());
		}
	}
	
	
	
	@Test
	public void testQuery2(){
		Query query = session.createQuery(" from EmployeeManager");
		List<Employee> list = query.list();
		for (Employee employee : list) {
			System.out.println(employee.getUsername());
		}
	}
}