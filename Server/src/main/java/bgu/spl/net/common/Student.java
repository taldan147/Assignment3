package bgu.spl.net.common;


import java.util.LinkedList;
import java.util.List;

public class Student {
    private String userName;
    private String password;
    private List<Course> registeredCourses;
    private boolean signedIn;

    public Student(String userName, String password){
        this.userName = userName;
        this.password = password;
        signedIn = true;
        registeredCourses = new LinkedList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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

    public void logIn(){ signedIn = true;}

    public void logOut(){signedIn = false;}
}
