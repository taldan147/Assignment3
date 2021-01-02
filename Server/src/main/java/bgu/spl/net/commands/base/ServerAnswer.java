package bgu.spl.net.commands.base;

import bgu.spl.net.commands.base.Message;
import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.List;

public class ServerAnswer extends Message {
    protected ServerAnswer(short serverCode, short clientCode) {
        super(serverCode, clientCode);
    }

    protected ServerAnswer(short opCode, List<String> params){
        super(opCode,params);
    }

    @Override
    public Serializable execute(Database arg) {
        return null;
    }
}
