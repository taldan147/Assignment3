package bgu.spl.net.common;

import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.util.List;

public  abstract class ClientMessage extends Message{

    private String username;

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
