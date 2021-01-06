package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.commands.base.Message;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MyCoursesCommand extends ClientMessage {
    public MyCoursesCommand(Short code) {
        super(code);
    }

    @Override
    public Serializable execute(Database arg) {
        if (username == null ||  !(arg.getUser(username) instanceof Student))
            return new Error(opcode, new LinkedList<>());
        List<String> params = new LinkedList<>();
        Student student = (Student) arg.getUser(username);
        List<Course> courses = student.getRegisteredCourses();
        if (courses.size()>0) {
            courses.sort(Comparator.comparingInt(Course::getId));
            StringBuilder courseString = new StringBuilder("\n[");
            for (Course course : courses) {
                courseString.append(course.getCourseNum()).append(",");
            }
            params.add((courseString.delete(courseString.length()-1,courseString.length()) + "]"));
        }
        else
            params.add("[]");
        return new Ack(opcode, params);
    }
}
