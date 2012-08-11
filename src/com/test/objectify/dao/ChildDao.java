package com.test.objectify.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.test.objectify.model.ChildEntity;
import com.test.objectify.model.ParentEntity;

public class ChildDao {
	
	static{
		ObjectifyService.register(ChildEntity.class);
	}
	
	public ChildEntity save(ChildEntity child) {
		ofy().save().entity(child).now();
		return child;
	}
	
	public List<ChildEntity> findAll() {
		List<ChildEntity> childs = ofy().load().type(ChildEntity.class).list();
		for(ChildEntity child:childs){
			System.out.println(child.getParent());
		}
		return childs;
	}
	
	public List<ChildEntity> findChildrensOfParent(ParentEntity parent) {
		List<ChildEntity> childs = ofy().load().type(ChildEntity.class).ancestor(parent).list();
		System.out.println("findChildrensOfParent size:"+childs.size());
		for(ChildEntity child:childs){
			System.out.println("findChildrensOfParent:"+child.getParent());
		}
		return childs;
	}
	
}
