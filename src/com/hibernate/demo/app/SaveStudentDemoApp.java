package com.hibernate.demo.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class SaveStudentDemoApp {

	public static void main(String[] args) {
		
		//Create a session Factory
		SessionFactory factory= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//create student object
			System.out.println("Creating student object..");
			Student student=new Student("Nik","Josh","nik@gmail.com");
			
			//start session
			session.beginTransaction();
			
			//add student object into session
			System.out.println("Saving student object into database");
			session.save(student);
			
			//end transaction and commit it
			session.getTransaction().commit();
			
			//show all students after Insertion
			session=factory.getCurrentSession();
			session.beginTransaction();
			List<Student> students=session.createQuery("from Student").getResultList();
					
			//display all students
			System.out.println("\n\nAll students data:");
			for(Student student1:students)
			{
				System.out.println(student1);
			}
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}finally {
			factory.close();
		}

	}

}
