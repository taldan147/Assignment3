package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ServerAnswer;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Ack extends ServerAnswer {
    public Ack(short opcode, List<String> parameters) {
        super(opcode, parameters);
    }
    @Override
    public byte[] serialize(){
        byte[] toReturn=new byte[4];
//        ByteBuffer buffer = ByteBuffer.allocate(4);
//        buffer.putShort((short)12).putShort(opcode);
//        toReturn =  buffer.array();
        toReturn[0]= (byte)((12 >> 8) & 0xFF);
        toReturn[1]= (byte) (12 & 0xFF);
        toReturn[2] = (byte)((opcode >> 8) & 0xFF);
        toReturn[3] = (byte) (opcode & 0xFF);
//        toReturn[0]=(byte)12;
//        toReturn[1]=(byte)opcode;
        for (String str:parameters) {
            toReturn=appendByteArray(toReturn,(str+", ").getBytes(StandardCharsets.UTF_8));
        }
        toReturn = appendByteArray(toReturn,"\0".getBytes(StandardCharsets.UTF_8));
        return toReturn;
    }

}
