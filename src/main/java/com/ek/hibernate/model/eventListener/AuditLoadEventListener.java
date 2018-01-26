package com.ek.hibernate.model.eventListener;

import org.hibernate.HibernateException;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.def.DefaultLoadEventListener;

public class AuditLoadEventListener extends DefaultLoadEventListener{

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		System.out.println("onLoad...........................................................................................");
		super.onLoad(event, loadType);
	}

}
