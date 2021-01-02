package bgu.spl.net.common;

import java.util.List;

public class Course {
    private int courseNum;
    private int id;
    private String courseName;
    private List<Course> kdamCourses;
    private int maxNumOfStudents;
    private int numOfRegisteredStudents;
    private boolean isInitialized;



    public Course(int courseNum,int id, String courseName, List<Course> courses,int maxNumOfStudents){
        this.courseNum = courseNum;
        this.id = id;
        this.courseName = courseName;
        this.maxNumOfStudents = maxNumOfStudents;
        numOfRegisteredStudents=0;
        this.kdamCourses=courses;
        isInitialized=true;
    }

    public Course(int course) {
        this.courseNum=course;
        isInitialized=false;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public boolean hasAvailable(){ return numOfRegisteredStudents < maxNumOfStudents;}

    public int getMaxNumOfStudents() {
        return maxNumOfStudents;
    }

    public void setMaxNumOfStudents(int maxNumOfStudents) {
        this.maxNumOfStudents = maxNumOfStudents;
    }

    public List<Course> getKdamCourses() {
        return kdamCourses;
    }

    public void registerStudent(){
        numOfRegisteredStudents++;
    }
    public void unregisterStudent(){
        numOfRegisteredStudents--;
    }

    public boolean isInitialized(){
        return isInitialized;
    }
}
