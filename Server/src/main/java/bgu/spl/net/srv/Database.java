package bgu.spl.net.srv;


import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;
import bgu.spl.net.common.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
    private Set<User> users;
    private Set<Course> courses;
    private int count;

    //to prevent user from creating new Database
    private Database() {
        users = new HashSet<>();
        courses = new HashSet<>();
        count = 0;
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
    public boolean initialize(String coursesFilePath) {
        File courses = new File(coursesFilePath);
        try (Scanner myReader = new Scanner(courses)) {
            while (myReader.hasNextLine()) {
                addToDB(parseLine(myReader.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        mergeCourses();
        return true;
    }

    private void mergeCourses() {
        for (Course course : courses) {
            if (!course.isInitialized()) {
                if (courses.stream().anyMatch(course1 ->
                        course1.isInitialized()
                                && course1.getCourseNum() == course.getCourseNum())) {
                    courses.remove(course);
                }
            }
        }
    }

    private void addToDB(List<String> parsedLines) {
        LinkedList<Course> kdams = new LinkedList<Course>();
        String[] kdamStrings = parsedLines.get(2).substring(1, parsedLines.get(2).length() - 1).split(",");
        for (String cour : kdamStrings) {
            if (!cour.equals("")) {
                kdams.add(new Course(Integer.parseInt(cour)));
            }
        }
        courses.add(new Course(Integer.parseInt(parsedLines.get(0)), count, parsedLines.get(1), kdams, Integer.parseInt(parsedLines.get(3))));
    }

    private LinkedList<String> parseLine(String nextLine) {
        String[] split = nextLine.split("\\|");
        return new LinkedList<>(Arrays.asList(split));
    }

    public Course getCourse(int courseNum) {
        return courses.stream().filter(course -> course.getCourseNum() == courseNum).findFirst().get();
    }

    public User getUser(String username) {
        return users.stream().filter(user -> user.getUserName().equals(username)).findFirst().get();
    }

    public boolean doesUserExists(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username))
                return true;
        }
        return false;
    }

	public boolean doesCourseExists(short courseNum){
    	for (Course course: courses){
    		if (course.getCourseNum() == courseNum)
    			return true;
		}
    	return false;
	}

    public void registerToCourse(String username, short courseNum){
    	Student student = (Student) getUser(username);
    	Course courseToRegister = getCourse(courseNum);
    	student.registerCourse(courseToRegister);
    	courseToRegister.registerStudent();
	}

    public void registerUser(User user) {
        users.add(user);
    }

    private static class DatabaseHolder {
        private static Database instance = new Database();
    }


}


