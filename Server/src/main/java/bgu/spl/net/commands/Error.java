package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ServerAnswer;

import java.util.List;

public class Error extends ServerAnswer {


    public Error(short opCode, List<String> params) {
        super(opCode, params);
    }

    @Override
    public byte[] serialize(){
        byte[] toReturn=new byte[4];
        toReturn[0]= (byte)((13 >> 8) & 0xFF);
        toReturn[1]= (byte) (13 & 0xFF);
        toReturn[2] = (byte)((opcode >> 8) & 0xFF);
        toReturn[3] = (byte) (opcode & 0xFF);
        return toReturn;
    }
}
