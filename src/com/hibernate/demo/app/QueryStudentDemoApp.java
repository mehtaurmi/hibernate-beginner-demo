package com.hibernate.demo.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class QueryStudentDemoApp {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {			
			//start session
			session.beginTransaction();
			
			//query students
			List<Student> students=session.createQuery("from Student").getResultList();
			
			//display students
			displayStudents(students);
			
			
			//query students with lastname:"Doe"
			students=session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			System.out.println("\n\nDisplay student with lastname='Doe'");
			displayStudents(students);
			
			
			//query students and display who has lastname="Doe" or firstname="john"
			students=session.createQuery("from Student s where s.lastName='Doe' or s.firstName='john'").getResultList();
			System.out.println("\n\nDisplay student with lastname='Doe' or Firstname='John'");
			displayStudents(students);
			
			//query to get students who has email address LIKE '%gmail.com'
			students=session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nDisplay student whose email ends with gmail.com");
			displayStudents(students);
			
			//end transaction and commit it
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> students) {
		for(Student student:students) {
			System.out.println(student);
		}
	}

}
