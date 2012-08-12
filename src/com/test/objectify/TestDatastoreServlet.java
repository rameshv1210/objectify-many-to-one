package com.test.objectify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.test.objectify.dao.ChildDao;
import com.test.objectify.dao.ParentDao;
import com.test.objectify.model.ChildEntity;
import com.test.objectify.model.GrandChildEntity;
import com.test.objectify.model.ParentEntity;

@SuppressWarnings("serial")
public class TestDatastoreServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Key k = new KeyFactory.Builder("ParentEntity", 5).addChild("ChildEntity",6).getKey();//.addChild("GrandChildEntity",7)
		
		Query q = new Query("GrandChildEntity");
		//q.setAncestor(k);
		PreparedQuery pq = datastore.prepare(q);
		
		for (Entity result : pq.asIterable()) {
		  String grandChildName = (String) result.getProperty("grandChildName");
		  System.out.println(result.getKey() + " : "+grandChildName);
		  resp.getWriter().println(result.getKey() + " : "+grandChildName);
		}
//		try{
//			Entity grandChild = datastore.get(k);
//			String grandChildName = (String) grandChild.getProperty("grandChildName");
//			System.out.println(grandChildName + " : "+grandChild.getKey());
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		

		
		
	}
}
