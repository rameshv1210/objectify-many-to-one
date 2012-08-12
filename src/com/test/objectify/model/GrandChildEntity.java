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
public class GrandChildEntity {
	
	@Id
    private Long id;
	
	private String grandChildName;
	
	@Load
	@Parent
    private Ref<ChildEntity> parent;
	
	
	

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
	public final ChildEntity getParent() {
		return parent.get();
	}

	/**
	 * @param parent the parent to set
	 */
	public final void setParent(ChildEntity parent) {
		this.parent = Ref.create(parent.getKey(), parent);
	}

	
	
	/**
     * @return the key
     */
    public Key<GrandChildEntity> getKey() {
    	if(id == null){
    		return null;
    	}
        return Key.create(this.getClass(), id);
    }
	
	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		tmp.append(" {id:").append(id);
		tmp.append(", grandChildName:").append(getGrandChildName());
		if(parent == null){
			tmp.append(", grand child parent is null. }");
		}else{
			tmp.append(", grand child parent is not null. } ");
		}
		return tmp.toString();
	}

	/**
	 * @param grandChildName the grandChildName to set
	 */
	public void setGrandChildName(String grandChildName) {
		this.grandChildName = grandChildName;
	}

	/**
	 * @return the grandChildName
	 */
	public String getGrandChildName() {
		return grandChildName;
	}
	
	
}
