package javaTest.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Parent extends EntityBase {

	@Column
	private String parentData;

	@OneToOne(mappedBy = "parent")
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	protected OneThing oneThing;

	@OneToMany(mappedBy = "parent")
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private final List<Child> childern = new ArrayList<Child>();

	public String getParentData() {
		return this.parentData;
	}

	public void setParentData(final String parentData) {
		this.parentData = parentData;
	}

	public List<Child> getChildern() {
		return this.childern;
	}

	public void addChild(final Child c) {
		if (c != null) {
			c.setParent(this);
			getChildern().add(c);
		}
	}

	public OneThing getOneThing() {
		return this.oneThing;
	}

	public void setOneThing(final OneThing oneThing) {
		this.oneThing = oneThing;
	}

}
