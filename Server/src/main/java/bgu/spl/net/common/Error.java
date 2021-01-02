package bgu.spl.net.common;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Error extends ServerAnswer{


    public Error(short opCode, List<String> params) {
        super(opCode, params);
    }

    @Override
    public byte[] serialize(){
        byte[] toReturn=new byte[2];
        toReturn[0]= 13;
        toReturn[1]= (byte) opcode;
//        ByteBuffer buffer = ByteBuffer.allocate(4);
//        buffer.putShort(opcode).putShort(Short.parseShort(parameters.get(0)));
//        buffer.get(toReturn);
//        toReturn=appendByteArray(toReturn,(Short.parseShort(parameters.get(0))));
        return toReturn;
    }
}
