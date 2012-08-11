package com.test.objectify.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;
import com.test.objectify.model.ChildEntity;
import com.test.objectify.model.ParentEntity;

public class ParentDao {
	
	static{
		ObjectifyService.register(ParentEntity.class);
	}
	
	//Not a very good practice
	public ChildDao childDao = new ChildDao();
	
	public ParentEntity save(ParentEntity parent) {
		ofy().save().entity(parent).now();
		return parent;
	}
	
	public List<ParentEntity> findAll() {
		List<ParentEntity> parents = ofy().load().type(ParentEntity.class).list();
		for(ParentEntity parent:parents){
			fillAllChildDetails(parent);
		}
		return parents;
	}
	
	private void fillAllChildDetails(ParentEntity parent){
		List<ChildEntity> childs = childDao.findChildrensOfParent(parent);
		parent.setChilds(childs);
		
	}
	
}
