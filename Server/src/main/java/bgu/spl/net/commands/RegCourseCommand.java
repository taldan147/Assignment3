package bgu.spl.net.commands;

import bgu.spl.net.commands.base.QueryMessage;
import bgu.spl.net.srv.Database;

import java.io.Serializable;

public class RegCourseCommand extends QueryMessage {
    public RegCourseCommand(short opcode, short queryNumber) {
        super(opcode, queryNumber);
    }

    @Override
    public Serializable execute(Database arg) {
        return null;
    }
}
