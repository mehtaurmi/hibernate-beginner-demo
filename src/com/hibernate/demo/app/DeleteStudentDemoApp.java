package com.hibernate.demo.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class DeleteStudentDemoApp {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
	
		//start new session
		Session newSession = factory.getCurrentSession();
		newSession.beginTransaction();
			
		//delete using id directly
		newSession.createQuery("delete Student s where s.id=5").executeUpdate();
		System.out.println("Student deleted");
			
		//commit the transaction
		newSession.getTransaction().commit();
		
		/*
		//delete using object
		int studentId=5;
		Session session1=factory.getCurrentSession();
		session1.beginTransaction();
		Student newStudent=session1.get(Student.class,studentId);
		System.out.println("Deleting Student:"+newStudent);
		session1.delete(newStudent);
		
		System.out.println("student deleted using object");
		session1.getTransaction().commit();
		*/
		
		//show all students after deletion
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		List<Student> students=session.createQuery("from Student").getResultList();
				
		//display all students
		System.out.println("\n\nAll students data:");
		for(Student student:students)
		{
			System.out.println(student);
		}
		session.getTransaction().commit();
		System.out.println("Done!");
			
			
		
		factory.close();
		

	}

}
