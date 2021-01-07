package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Student;
import bgu.spl.net.common.User;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.LinkedList;

public class UnregisterCommand extends QueryMessage {
    public UnregisterCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);
    }

    @Override
    public Serializable execute(Database arg) {
        if (username == null || arg.getUser(username) instanceof Admin || !((Student)arg.getUser(username)).isRegistered(arg.getCourse(query))
                || !arg.doesCourseExists(query))
            return new Error(opcode, new LinkedList<>());
        arg.unregisterCourse((Student)arg.getUser(username), arg.getCourse(query));
        return new Ack(opcode, new LinkedList<>());
    }
}
