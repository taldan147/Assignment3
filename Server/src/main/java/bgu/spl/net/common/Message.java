package bgu.spl.net.common;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.List;

public class Message implements Serializable {
    short opcode;
    List<String> parameters;

    public Message(short opcode, List<String> parameters){
        this.opcode = opcode;
        this.parameters = parameters;
    }

    public byte[] serialize(){
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(opcode);
        byte[] shortby buffer.array();
    }

    public List<String> getParameters(){ return parameters;}

    public short getOpcode(){ return opcode;}
}
