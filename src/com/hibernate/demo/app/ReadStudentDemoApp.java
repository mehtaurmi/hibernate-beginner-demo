package com.hibernate.demo.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemoApp {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create student object
			System.out.println("Creating student object..");
			Student student=new Student("John","Doe","john@gmail.com");
			
			//start session and add into session
			session.beginTransaction();
			System.out.println("Student object is:"+student);
			session.save(student);
			
			//end transaction and commit it
			session.getTransaction().commit();
			System.out.println("Student saved with ID:"+student.getId());
			
			
			//new code
			//start new session
			Session newSession = factory.getCurrentSession();
			newSession.beginTransaction();
			
			//retrieve student based on given id
			Student newStudent=newSession.get(Student.class, student.getId());
			
			//commit the transaction
			newSession.getTransaction().commit();
			System.out.println("Student found:"+newStudent);
			
			
			
			System.out.println("Done!");
			
			
		}finally {
			factory.close();
		}

	}

}
