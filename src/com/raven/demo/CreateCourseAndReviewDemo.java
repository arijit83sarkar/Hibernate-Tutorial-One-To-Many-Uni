package com.raven.demo;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;
import com.raven.entity.Review;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

	public static void main(String[] args) throws Exception {
		System.out.println(">>>>>>> Welcome One-To-Many Create Course And Reviews Demo!!! <<<<<<<<");
		SessionFactory sessionFactory = null;
		Session session = null;

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class)
					.addAnnotatedClass(Review.class).buildSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// create course
			Course course = new Course("Pacman - How to score one million point.");

			// add review
			course.addReview(new Review("Greate course...loved it!"));
			course.addReview(new Review("Cool course, job well done!"));
			course.addReview(new Review("What a dumb course, it can be better."));

			// save course
			// this will also save all the reviews
			session.save(course);

			session.getTransaction().commit();
			System.out.println(">>> Done <<<");
		} catch (Exception exception) {
			// exception.printStackTrace();
			System.out.println(">>> ERROR >>> " + exception.getLocalizedMessage());
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
