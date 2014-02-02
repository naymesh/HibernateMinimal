package javaTest.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class GrandChild extends EntityBase {
	@Column
	private String grandChildData;

	@ManyToOne
	private Child parent;

	public String getGrandChildData() {
		return this.grandChildData;
	}

	public void setGrandChildData(final String grandChildData) {
		this.grandChildData = grandChildData;
	}

	public Child getParent() {
		return this.parent;
	}

	public void setParent(final Child parent) {
		this.parent = parent;
	}

}
