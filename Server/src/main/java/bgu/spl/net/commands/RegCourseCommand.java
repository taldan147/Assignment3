package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Student;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.LinkedList;

public class RegCourseCommand extends QueryMessage {
    public RegCourseCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);
    }

    @Override
    public Serializable execute(Database arg) {
        // if student is logoff username is null
        if (username == null || arg.getUser(username) instanceof Admin || !arg.doesCourseExists(query))
            return new Error(opcode, new LinkedList<>());
        synchronized (arg.getCourse(query)){
            if (!arg.getCourse(query).hasAvailable() || !((Student)arg.getUser(username)).hasAllKdam(query))
                return new Error(opcode, new LinkedList<>());
            arg.registerToCourse(username, query);
            return new Ack(opcode, new LinkedList<>());
        }

    }
}
