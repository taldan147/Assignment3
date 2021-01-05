package bgu.spl.net.srv;


import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;
import bgu.spl.net.common.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
    private ConcurrentHashMap<String, User> users;
    private ConcurrentHashMap<Integer, Course> courses;
//    private Set<User> users;
//    private Set<Course> courses;
    private int count;

    //to prevent user from creating new Database
    private Database() {
        users = new ConcurrentHashMap<String,User>();
        courses = new ConcurrentHashMap<Integer,Course>();
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
        removeRedundantCourses();
        return true;
    }

    private void mergeCourses() {
        for (Course course : courses.values()) {
            if (course.isInitialized()) {
                List<Course> kdamCourses=course.getKdamCourses();
                for(int i=0;i<kdamCourses.size();i++){
                    Course finalKdam = kdamCourses.get(i);
                    kdamCourses.set(i,courses.values().stream().filter(course1 -> course1.getCourseNum()== finalKdam.getCourseNum()&&course1.isInitialized()).collect(Collectors.toList()).get(0));
                }

            }
        }
    }

    private void removeRedundantCourses() {
        for (Course course : courses.values()) {
            if (!course.isInitialized()) {
                if (courses.values().stream().anyMatch(course1 ->
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
        courses.put(Integer.parseInt(parsedLines.get(0)),new Course(Integer.parseInt(parsedLines.get(0)), count, parsedLines.get(1), kdams, Integer.parseInt(parsedLines.get(3))));
        count++;
    }

    private LinkedList<String> parseLine(String nextLine) {
        String[] split = nextLine.split("\\|");
        return new LinkedList<>(Arrays.asList(split));
    }

    public Course getCourse(int courseNum) {
        return courses.get(courseNum);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean doesUserExists(String username) {
        return users.containsKey(username);
    }

	public boolean doesCourseExists(int courseNum){
    	return courses.containsKey(courseNum);
	}

    public void registerToCourse(String username, short courseNum){
    	Student student = (Student) getUser(username);
    	Course courseToRegister = getCourse(courseNum);
    	student.registerCourse(courseToRegister);
    	courseToRegister.registerStudent(username);
	}

    public void registerUser(User user) {
        users.put(user.getUserName(), user);
    }

    private static class DatabaseHolder {
        private static Database instance = new Database();
    }

    public void unregisterCourse(Student student, Course course){
        student.unregisterCourse(course);
        course.unregisterStudent(student.getUserName());
    }
}


