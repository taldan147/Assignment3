package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class LogInCommand extends ClientMessage {
    public LogInCommand(short opcode, List<String> parameters) {
        super(opcode, parameters);
    }

    @Override
    public Serializable execute(Database arg) {
        synchronized (arg.getUsers()) {
            String username = parameters.get(0);
            String password = parameters.get(1);
            if (!arg.doesUserExists(username) || !arg.getUser(username).getPassword().equals(password) || arg.getUser(username).isSignedIn())
                return new Error(opcode, new LinkedList<>());
            arg.getUser(username).logIn();
            return new Ack(opcode, new LinkedList<>());
        }
    }
}
