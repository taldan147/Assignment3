package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Course;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class KdamCheckCommand extends QueryMessage {
    public KdamCheckCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);
    }

    @Override
    public Serializable execute(Database arg) {
        if (arg.getUser(username)instanceof Admin || !arg.doesCourseExists(query)){
            return new Error(opcode,new LinkedList<>());
        }
        List<String> params = new LinkedList<>();
        params.add("\n"+ arg.getCourse(query)
                .getKdamCourses().stream().sorted(Comparator.comparingInt(Course::getId))
                .map(course
                        -> course.getCourseNum()).collect(Collectors.toList()).toString().replaceAll(" ",""));

        return new Ack(opcode, params);
    }
}
