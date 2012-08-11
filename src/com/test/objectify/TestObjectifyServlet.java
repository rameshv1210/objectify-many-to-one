package com.test.objectify;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.test.objectify.dao.ChildDao;
import com.test.objectify.dao.ParentDao;
import com.test.objectify.model.ChildEntity;
import com.test.objectify.model.ParentEntity;

@SuppressWarnings("serial")
public class TestObjectifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		ParentDao parentDao = new ParentDao();
		List<ParentEntity> parents = parentDao.findAll();
		ParentEntity parentEntity = null;
		if(parents.size() < 5){
			for(int i =1; i<=5;i++ ){
				parentEntity = new ParentEntity();
				parentEntity.setName("Parent Name"+i);
				parentDao.save(parentEntity);
			}
			
		}else{
			//Random Number generation standard pattern Min + (int)(Math.random() * ((Max - Min) + 1))
			parentEntity = parents.get(0 + (int)(Math.random() * ((4 - 0) + 1)));
		}
		
		ChildDao childDao = new ChildDao();
		
		ChildEntity childEntity = new ChildEntity();
		childEntity.setChildName("Child Name");
		childEntity.setParent(parentEntity);
		
		childDao.save(childEntity);
		
		resp.getWriter().println("All Parents:\n"+parentDao.findAll());
	}
}
