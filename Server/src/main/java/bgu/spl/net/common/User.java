package bgu.spl.net.common;

import bgu.spl.net.srv.Database;

import java.util.LinkedList;
import java.util.List;

public abstract class User {
    protected String userName;
    protected String password;
    protected boolean signedIn;
    protected Database database;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.signedIn = false;
        this.database = Database.getInstance();
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean getSignedIn(){ return signedIn; }

    public void logIn(){ signedIn = true;}

    public void logOut(){signedIn = false;}

    public List<Integer> checkKdamCourse(int courseNumber){
        List<Course> kdamCourses = database.getCourse(courseNumber).getKdamCourses();
        List<Integer> toReturn = new LinkedList<>();
        for (Course course: kdamCourses){
            toReturn.add(course.getCourseNum());
        }
        return toReturn;
    }
}
