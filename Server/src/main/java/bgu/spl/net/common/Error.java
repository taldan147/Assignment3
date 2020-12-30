package bgu.spl.net.common;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Error extends Message{
    public Error(short opcode, List<String> parameters) {
        super(opcode, parameters);
    }

    @Override
    public byte[] serialize(){
        byte[] toReturn=null;
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(opcode);
        toReturn =  buffer.array();
        toReturn=appendByteArray(toReturn,(parameters.get(0)).getBytes(StandardCharsets.UTF_8));
        return toReturn;
    }
}
