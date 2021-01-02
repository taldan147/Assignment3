package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class LogOutCommand extends ClientMessage {
    public LogOutCommand(short opcode, List<String> parameters) {
        super(opcode, parameters);

    }

    @Override
    public Serializable execute(Database arg) {
        arg.getUser(username).logOut();
        return new Ack(opcode, new LinkedList<>());
    }
}
