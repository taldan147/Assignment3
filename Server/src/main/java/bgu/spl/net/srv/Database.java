package bgu.spl.net.srv;


import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;

import java.util.HashSet;
import java.util.Set;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
	private Set<Student> students;
	private Set<Course> courses;

	//to prevent user from creating new Database
	private Database() {
		students = new HashSet<>();
		courses = new HashSet<>();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Database getInstance() {
		return DatabaseHolder.instance;
	}
	
	/**
	 * loades the courses from the file path specified 
	 * into the Database, returns true if successful.
	 */
	boolean initialize(String coursesFilePath) {
		// TODO: implement
		return false;
	}


	private static class DatabaseHolder{
		private static Database instance = new Database();
	}



}


