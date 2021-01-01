package bgu.spl.net.commands;

import bgu.spl.net.common.ClientMessage;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.List;

public class LoginCommand extends ClientMessage {


    protected LoginCommand(short opcode, List<String> parameters) {
        super(opcode, parameters);
    }

    @Override
    public Serializable execute(Database arg) {
        return null;
    }
}
