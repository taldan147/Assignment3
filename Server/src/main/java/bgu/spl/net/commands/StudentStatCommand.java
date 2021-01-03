package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class StudentStatCommand extends ClientMessage {

    private String studentName;

    public StudentStatCommand(short opcode, String studentName) {
        super(opcode);
        this.studentName = studentName;
    }

    @Override
    public Serializable execute(Database arg) {
        if (username == null || !(arg.getUser(studentName) instanceof Student) || !(arg.getUser(username) instanceof Admin))
            return new Error(opcode, new LinkedList<>());
        List<String> params = new LinkedList<>();
        Student student = (Student) arg.getUser(studentName);
        List<Course> courses = student.getRegisteredCourses();
        courses.sort(Comparator.comparingInt(Course::getId));
        StringBuilder courseString = new StringBuilder("\nCourses: [");
        for (Course course : courses) {
            courseString.append(course.getCourseName()).append(", ");
        }
        params.add("\nStudent: " + studentName);
        params.add(courseString.delete(courseString.length()-2,courseString.length()) + "]");
        return new Ack(opcode, params);
    }
}
//
//class SortbyId implements Comparator<Course>
//{
//    // Used for sorting in descending order of id
//
//    @Override
//    public int compare(Course course, Course t1) {
//        return co
//    }
//}