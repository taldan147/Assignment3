package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class StudentStatCommand extends QueryMessage {

    private String studentName;

    protected StudentStatCommand(short opcode, short queryNumber, String studentName) {
        super(opcode, queryNumber);
        this.studentName = studentName;
    }

    @Override
    public Serializable execute(Database arg) {
        if (username == null || arg.getUser(studentName) instanceof Student|| arg.getUser(username) instanceof Admin)
            return new Error(opcode, new LinkedList<>());
        List<String> params = new LinkedList<>();
        Student student = (Student) arg.getUser(studentName);
        List<Course> courses = student.getRegisteredCourses();
        courses.sort((course, t1) -> t1.getId() - course.getId());
        StringBuilder courseString = new StringBuilder("\nCourses: [");
        for (Course course : courses) {
            courseString.append(course.getCourseName()).append(", ");
        }
        params.add("\nStudent: " + username);
        params.add(courseString + "]");
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