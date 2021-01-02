package bgu.spl.net.commands.base;

import bgu.spl.net.srv.Database;

import java.io.Serializable;

public abstract class QueryMessage extends ClientMessage {
    private short query;

    protected QueryMessage(short opcode, short queryNumber) {
        super(opcode);
        query = queryNumber;
    }

}
