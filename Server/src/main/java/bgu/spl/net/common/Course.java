package bgu.spl.net.common;

import java.util.List;

public class Course {
    private int courseNum;
    private int id;
    private String courseName;
    private List<Course> kdamCourses;
    private int maxNumOfStudents;

    public Course(int courseNum,int id, String courseName, List<Course> kdamCourses, int maxNumOfStudents){
        this.courseNum = courseNum;
        this.id = id;
        this.courseName = courseName;
        this.kdamCourses = kdamCourses;
        this.maxNumOfStudents = maxNumOfStudents;
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
}
