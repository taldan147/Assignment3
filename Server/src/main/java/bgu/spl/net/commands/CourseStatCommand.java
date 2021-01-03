package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Course;
import bgu.spl.net.common.Student;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CourseStatCommand extends QueryMessage {
    public CourseStatCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);
    }

    @Override
    public Serializable execute(Database arg) {
        if (username == null || arg.getUser(username) instanceof Student || !arg.doesCourseExists(query))
            return new Error(opcode, new LinkedList<>());
        List<String> params = new LinkedList<>();
        Course course = arg.getCourse(query);
        params.add("Course: (" + course.getCourseNum()+ ") "+ course.getCourseName()+ "\n");
        params.add("Seats Available: " + course.getNumOfRegisteredStudents() + "/" + course.getMaxNumOfStudents() + "\n");
        params.add("Students Registered: " + course.getStudentsStrList());
        return new Ack(opcode, params);
    }
}
