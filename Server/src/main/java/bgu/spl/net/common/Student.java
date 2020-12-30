package bgu.spl.net.common;


import java.util.LinkedList;
import java.util.List;

public class Student extends User {

    private List<Course> registeredCourses;


    public Student(String userName, String password){
        super(userName, password);
        registeredCourses = new LinkedList<>();
    }



    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course registerCourse) {
        registeredCourses.add(registerCourse);
        registerCourse.registerStudent();
    }
    public void unregisterCourse(Course course){
        registeredCourses.remove(course);
        course.unregisterStudent();
    }



}
