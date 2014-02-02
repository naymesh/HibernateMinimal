package javaTest.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateEntityProcessor {
	private static final SessionFactory sessionFactory;
	static {
		try {
			final AnnotationConfiguration config = new AnnotationConfiguration();
			sessionFactory = config.configure().buildSessionFactory();

			new SchemaExport(config).create(true, true);
		} catch (final Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static void main(final String[] args) {
		final Session session = getSession();

		System.out.println("****Part1*****");
		session.beginTransaction();
		final Parent p = new Parent();
		p.setParentData("parentData");
		final OneThing o = new OneThing(p);
		o.setOneThingData("oneThingData");
		session.save(p);
		session.getTransaction().commit();

		System.out.println("****Part2*****");
		session.beginTransaction();
		final Parent loadedParent = (Parent) session.get(Parent.class, new Integer(1));
		loadedParent.setOneThing(null);
		session.save(loadedParent);
		session.getTransaction().commit();

		System.out.println("Done");
	}

	public static void testThreeLevelParentChild() {
		final Session session = getSession();
		session.beginTransaction();

		final Parent p = new Parent();
		p.setParentData("parentData");

		final Child c = new Child();
		c.setChildData("childData");

		final GrandChild gc = new GrandChild();
		gc.setGrandChildData("grandChildData1");
		c.addGrandChild(gc);

		final GrandChild gc2 = new GrandChild();
		gc2.setGrandChildData("grandChildData2");
		c.addGrandChild(gc2);

		p.addChild(c);

		session.save(p);
		session.getTransaction().commit();
		session.flush();

		session.beginTransaction();

		System.out.println("****Part2******");

		final Parent loadedParent = (Parent) session.get(Parent.class, new Integer(1));

		final Child loadedChild = loadedParent.getChildern().get(0);
		loadedChild.getGrandChildren().remove(1);

		final GrandChild gc3 = new GrandChild();
		gc3.setGrandChildData("grandChildData3");
		loadedChild.addGrandChild(gc3);

		session.flush();
		session.getTransaction().commit();

		session.close();
	}
}
