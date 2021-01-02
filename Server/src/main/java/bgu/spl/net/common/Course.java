package bgu.spl.net.common;

import java.util.List;

public class Course {
    private int courseNum;
    private int id;
    private String courseName;
    private List<Course> kdamCourses;
    private int maxNumOfStudents;
    private int numOfRegiisteredStrudents;
    private List<Student> registeredStudents;
    private boolean isInitialized;



    public Course(int courseNum,int id, String courseName, List<Course> courses,int maxNumOfStudents){
        this.courseNum = courseNum;
        this.id = id;
        this.courseName = courseName;
        this.maxNumOfStudents = maxNumOfStudents;
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
        numOfRegiisteredStrudents++;
    }
    public void unregisterStudent(){
        numOfRegiisteredStrudents--;
    }

    public boolean isInitialized(){
        return isInitialized;
    }
}
