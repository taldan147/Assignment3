package bgu.spl.net.commands;

import bgu.spl.net.common.Message;
import bgu.spl.net.impl.rci.Command;
import bgu.spl.net.srv.Database;

public abstract class BGRSCommand implements Command<Message> {
    short code;
    public BGRSCommand(short opcode){
        this.code = opcode;
    }

}
