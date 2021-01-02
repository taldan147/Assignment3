package bgu.spl.net.common;

import bgu.spl.net.impl.rci.Command;
import bgu.spl.net.srv.Database;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Message implements Command<Database> {
    protected short opcode;
    protected List<String> parameters;

    protected Message(short opcode, List<String> parameters){
        this.opcode = opcode;
        this.parameters = parameters;
    }

    protected Message(short opcode){
        this.opcode = opcode;
        this.parameters = null;
    }

    // will be used to create answers on the server
    protected Message(short serverCode, short clientCode){
        this.opcode = serverCode;
        parameters = new LinkedList<String>(Arrays.asList(String.valueOf(clientCode)));
    }

    public byte[] serialize(){
        byte[] toReturn=null;
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(opcode);
        toReturn =  buffer.array();

        for (String str:parameters) {
            toReturn=appendByteArray(toReturn,("\0"+str).getBytes(StandardCharsets.UTF_8));
        }
        return toReturn;
    }

    public List<String> getParameters(){ return parameters;}

    public short getOpcode(){ return opcode;}

    public byte[] appendByteArray(byte[] arr1, byte[] arr2){
        byte[] toReturn = new byte[arr1.length+arr2.length];
        for (int i = 0; i< arr1.length; i++){
            toReturn[i] = arr1[i];
        }
        for (int i=arr1.length;i<toReturn.length;i++){
            toReturn[i]=arr2[i-arr1.length];
        }
        return toReturn;
    }
}
