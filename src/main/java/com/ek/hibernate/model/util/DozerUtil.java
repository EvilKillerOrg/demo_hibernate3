package com.ek.hibernate.model.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerUtil {
	private static org.apache.commons.logging.Log log = LogFactory.getLog(DozerUtil.class);
	
	private static Mapper mapper=null;
	
	public static Mapper getMapper(){
		if(mapper!=null){
			 if(log.isDebugEnabled()){
				 log.debug("return dozer mapper ( not null ) ! >>>>>>>>>>>>>>>>>>>>>>>>>..");
			 }
			return mapper;
		}
		 List<String> mappingfilelist=new ArrayList<String>();
		 mappingfilelist.add("dozerBeanMapping.xml");
		 mapper = new DozerBeanMapper(mappingfilelist);
		 if(log.isDebugEnabled()){
			 log.debug("return dozer mapper ! >>>>>>>>>>>>>>>>>>>>>>>>>..");
		 }
		 return mapper;
	}
}