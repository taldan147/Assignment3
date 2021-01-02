package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Course;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class KdamCheckCommand extends QueryMessage {
    public KdamCheckCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);
    }

    @Override
    public Serializable execute(Database arg) {
        return new Ack(opcode,arg.getCourse(query)
                .getKdamCourses().stream()
                .map(course
                        -> String.valueOf(course.getCourseNum()))
                .collect(Collectors.toList()));
    }
}
