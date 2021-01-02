package bgu.spl.net.commands.base;

import bgu.spl.net.commands.base.Message;

import java.util.List;

public  abstract class ClientMessage extends Message {

    protected String username = null;

    protected ClientMessage(short opcode, List<String> parameters) {
        super(opcode, parameters);
    }
    protected ClientMessage(short opcode){
        super(opcode);
    }

    public void setUsername(String user){
        username = user;
    }

    public String getUsername(){ return username;}


}
