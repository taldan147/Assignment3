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
        byte[] toReturn=new byte[2];
//        ByteBuffer buffer = ByteBuffer.allocate(4);
//        buffer.putShort((short)12).putShort(opcode);
//        toReturn =  buffer.array();
        toReturn[0]=(byte)12;
        toReturn[1]=(byte)opcode;
        for (String str:parameters) {
            toReturn=appendByteArray(toReturn,(str).getBytes(StandardCharsets.UTF_8));
        }
        toReturn = appendByteArray(toReturn,"\0".getBytes(StandardCharsets.UTF_8));
        return toReturn;
    }

}
