package bgu.spl.net.common;

import java.util.List;

public class Course {

    private int courseNum;
    private String courseName;
    private List<Integer> kdamCourses;
    private int maxNumOfStudents;

    public Course(int courseNum, String courseName, List<Integer> kdamCourses, int maxNumOfStudents){
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.kdamCourses = kdamCourses;
        this.maxNumOfStudents = maxNumOfStudents;
    }

    public int getCourseNum() {
        return courseNum;
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

    public List<Integer> getKdamCourses() {
        return kdamCourses;
    }
}
