package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Student;
import bgu.spl.net.srv.Database;


import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

public class IsRegisteredCommand extends QueryMessage {
    public IsRegisteredCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);

    }

    @Override
    public Serializable execute(Database arg) {
        if (username == null || arg.getUser(username) instanceof Admin || !arg.doesCourseExists(query))
            return new Error(opcode, new LinkedList<>());
        Student student = (Student)arg.getUser(username);
        if (student.isRegistered(arg.getCourse(query)))
            return new Ack(opcode, new LinkedList<>(Arrays.asList("\nREGISTERED")));
        return new Ack(opcode, new LinkedList<>(Arrays.asList("\nNOT REGISTERED")));
    }
}
