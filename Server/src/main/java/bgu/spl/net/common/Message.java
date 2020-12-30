package bgu.spl.net.common;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    short opcode;
    List<String> parameters;

    public Message(short opcode, List<String> parameters){
        this.opcode = opcode;
        this.parameters = parameters;
    }

    public String serialize(){
        throw new NotImplementedException();
    }

    public List<String> getParameters(){ return parameters;}
}
