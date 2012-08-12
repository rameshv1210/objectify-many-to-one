package com.test.objectify.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.SimpleQuery;
import com.test.objectify.model.ChildEntity;
import com.test.objectify.model.GrandChildEntity;
import com.test.objectify.model.ParentEntity;

public class GrandChildDao {
	
	static{
		ObjectifyService.register(GrandChildEntity.class);
	}
	
	public GrandChildEntity save(GrandChildEntity child) {
		ofy().save().entity(child).now();
		return child;
	}
	
	public List<GrandChildEntity> findAll() {
		List<GrandChildEntity> childs = ofy().load().type(GrandChildEntity.class).list();
		for(GrandChildEntity child:childs){
			System.out.println(child.getParent());
		}
		return childs;
	}
	
	public List<GrandChildEntity> findGrandChildrensOfChild(ChildEntity parent) {
		List<GrandChildEntity> grandChilds = ofy().load().type(GrandChildEntity.class).ancestor(parent).list();
		
		//this is to overcome the issue of objectify, https://github.com/stickfigure/objectify/issues/1
		if(grandChilds==null || grandChilds.size() == 0){
			grandChilds = ofy().load().type(GrandChildEntity.class).ancestor(new KeyFactory.Builder("ChildEntity",parent.getId()).getKey()).list();
			System.out.println("findGrandChildrensOfChild size:"+grandChilds.size());
			for(GrandChildEntity grandChild:grandChilds){
				//But this fix fails to load the grandChilds parent, as the ref key varies in the hierarchy
				System.out.println("grandChildParent:"+grandChild.getParent());
			}
		}
		return grandChilds;
	}
	
}
