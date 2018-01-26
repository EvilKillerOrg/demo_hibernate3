package com.ek.hibernate.model.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ek.hibernate.model.util.HibernateSessionFactory;
/**
 * 把事务定义在这个过滤器中 执行.do请求 进入action 在action中调用service , service调用dao的时候就可以执行这里定义的事务
 * 就不必在每个service中写事务代码了
 */
public class HibernateFilter implements Filter {
	
	//commons log 继承了org.apache.log4j.Logger 和 java.util.logging.Logger ,一般企业都是用这个 这样更灵活
	private static org.apache.commons.logging.Log log = LogFactory.getLog(HibernateFilter.class);
			
			
	 
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)throws IOException, ServletException {
		Session session = HibernateSessionFactory.getSession();
		Transaction tr =null; 
		try {
			tr= session.beginTransaction();
			chain.doFilter(arg0, arg1);
			tr.commit();
			if(log.isDebugEnabled()){
				log.debug(" Transaction Commit Successfully ! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
			}
		} catch (HibernateException e) {
			log.error(" Hibernate Exception ! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..",e);
			if(tr!=null){
				tr.rollback();
			}
			//e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		
		//和上面没关系 需要用的时候装换 比如需要getContextPath
		//ServletRequest装换成HttpServletRequest  ServletRequest是HttpServletRequest的父接口
//		HttpServletRequest request = (HttpServletRequest)arg0;
//		request.getContextPath();
	}
	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		if(log.isDebugEnabled()){
			log.debug("Initialized Hibernate start.... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
		}
		// filter是在服务器启动的时候加载的 , 在这里使用一下HibernateSessionFactory 这个类 就可以在启动的时候加载了这个类
		// 注意不能使用HibernateSessionFactory 的 rebuildSessionFactory方法
		HibernateSessionFactory.getSessionFactory();//这里使用一些getSessionFactory 就触发了static代码块的执行
		if(log.isDebugEnabled()){
			log.debug("Initialized Hibernate end.... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
		}
	}

}
