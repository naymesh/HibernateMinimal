/* 
 * Copyright (c) Orchestral Developments Ltd (2001 - 2011).
 * 
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
package javaTest.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OneThing extends EntityBase {

	@Column
	private String oneThingData;

	@OneToOne
	@JoinColumn(name = "patentId", nullable = false)
	private final Parent parent;

	OneThing(final Parent parent) {
		if (parent == null) {
			throw new IllegalArgumentException("OneThing cant exist without a parent.");
		}
		this.parent = parent;
	}

	public Parent getParent() {
		return this.parent;
	}

	public String getOneThingData() {
		return this.oneThingData;
	}

	public void setOneThingData(final String oneThingData) {
		this.oneThingData = oneThingData;
	}

}
