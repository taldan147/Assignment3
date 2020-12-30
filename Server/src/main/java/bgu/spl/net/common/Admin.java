package bgu.spl.net.common;

public class Admin extends User{

    public Admin(String username, String password){
        super(username, password);
    }

    public Course checkCourseStat(int courseNumber){
        return database.getCourse(courseNumber);
    }

    public User getStudentStat(String username){
        return database.getUser(username);
    }
}
