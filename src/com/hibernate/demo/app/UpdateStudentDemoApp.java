package com.hibernate.demo.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class UpdateStudentDemoApp {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		//start session
		session.beginTransaction();
			
		//update student  whose firstname="john"
		System.out.println("Updating database...");
		session.createQuery("update Student s set s.firstName='Mary' where s.firstName='john'").executeUpdate();
			
			
		//end transaction and commit it
		session.getTransaction().commit();
		
		//update email for all students
		//Create and start session
		session=factory.getCurrentSession();
		session.beginTransaction();
		
		//update all students
		System.out.println("\n\nUpdating all students email=foo@gmail.com");
		session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
		
		//end transaction and commit it
		session.getTransaction().commit();
		
		//create and start new session to display all students from database
		session=factory.getCurrentSession();
		session.beginTransaction();
		
		//retrieve all student from database
		List<Student> students=session.createQuery("from Student").getResultList();
		
		//display all students
		System.out.println("\n\nAll students data:");
		for(Student student:students)
		{
			System.out.println(student);
		}
		
		//end transaction and commit it
		session.getTransaction().commit();
		
		System.out.println("Done!");
			
		
		factory.close();

	}

}
