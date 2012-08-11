package com.test.objectify.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Index//Optional
public class ChildEntity {
	
	@Id
    private Long id;
	
	@Load
	@Parent
    private Ref<ParentEntity> parent;
	
	private String childName;

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
	 * @return the parent
	 */
	public final ParentEntity getParent() {
		return parent.get();
	}

	/**
	 * @param parent the parent to set
	 */
	public final void setParent(ParentEntity parent) {
		this.parent = Ref.create(parent.getKey(), parent);;
	}

	/**
	 * @return the childName
	 */
	public final String getChildName() {
		return childName;
	}

	/**
	 * @param childName the childName to set
	 */
	public final void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
     * @return the key
     */
    public Key<ChildEntity> getKey() {
    	if(id == null){
    		return null;
    	}
        return Key.create(this.getClass(), id);
    }
	
	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		tmp.append(" {id:").append(id);
		tmp.append(", childName:").append(childName);
		if(parent == null){
			tmp.append(", parent is null. }");
		}else{
			tmp.append(", parent is not null. } ");
		}
		return tmp.toString();
	}
	
	
}
