package javaTest.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Child extends EntityBase {
	@Column
	private String childData;

	@ManyToOne
	private Parent parent;

	@OneToMany(mappedBy = "parent")
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private final List<GrandChild> grandChildren = new ArrayList<GrandChild>();

	public String getChildData() {
		return this.childData;
	}

	public void setChildData(final String childData) {
		this.childData = childData;
	}

	public Parent getParent() {
		return this.parent;
	}

	public void setParent(final Parent parent) {
		this.parent = parent;
	}

	public List<GrandChild> getGrandChildren() {
		return this.grandChildren;
	}

	public void addGrandChild(final GrandChild gc) {
		if (gc != null) {
			gc.setParent(this);
			getGrandChildren().add(gc);
		}
	}
}
