package com.test.objectify.model;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index//Optional
public class ParentEntity {
	
	@Id
    private Long id;
	
	private String name;
	
	@Ignore 
	private List<ChildEntity> childs;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
	/**
     * @return the key
     */
    public Key<ParentEntity> getKey() {
    	if(id == null){
    		return null;
    	}
        return Key.create(this.getClass(), id);
    }
	
	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		tmp.append(" {id:").append(id);
		tmp.append(", name:").append(name);
		tmp.append(", [childs(").append(getChilds().size()).append(") : ").append(childs).append("]} ");
		tmp.append("\n");
		return tmp.toString();
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<ChildEntity> childs) {
		this.childs = childs;
	}

	/**
	 * @return the childs
	 */
	public List<ChildEntity> getChilds() {
		if(this.childs == null){
			this.childs = new ArrayList<ChildEntity>();
		}
		return childs;
	}
}
