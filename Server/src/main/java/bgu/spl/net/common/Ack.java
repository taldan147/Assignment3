package bgu.spl.net.common;

import bgu.spl.net.srv.Database;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Ack extends ServerAnswer{
    public Ack(short opcode, List<String> parameters) {
        super(opcode, parameters);
    }
    @Override
    public byte[] serialize(){
        byte[] toReturn=null;
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(opcode);
        toReturn =  buffer.array();

        for (String str:parameters) {
            toReturn=appendByteArray(toReturn,(str).getBytes(StandardCharsets.UTF_8));
        }
        toReturn = appendByteArray(toReturn,"\0".getBytes(StandardCharsets.UTF_8));
        return toReturn;
    }

}
