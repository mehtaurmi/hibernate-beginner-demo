package com.hibernate.demo.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create student object
			System.out.println("Creating 3 student object..");
			Student student1=new Student("Vid","Josh","vid@gmail.com");
			Student student2=new Student("Tar","Meh","tar@gmail.com");
			Student student3=new Student("Man","Sha","man@gmail.com");
			
			//start session
			session.beginTransaction();
			
			//add student object into session
			System.out.println("Saving student object into database");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			//end transaction and commit it
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}

	}

}
