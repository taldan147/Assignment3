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
    }
    public void unregisterCourse(Course course){
        registeredCourses.remove(course);
    }

    public boolean hasAllKdam(short courseNum){
        List<Course> kdamCourses = database.getCourse(courseNum).getKdamCourses();
        for (Course course : kdamCourses){
            if (!registeredCourses.contains(course))
                return false;
        }
        return true;
    }

    public boolean isRegistered(Course course){ return registeredCourses.contains(course);}




}
