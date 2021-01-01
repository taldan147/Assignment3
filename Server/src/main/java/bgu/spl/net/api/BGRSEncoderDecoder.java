package bgu.spl.net.api;

import bgu.spl.net.commands.RegistrationCommand;
import bgu.spl.net.common.Error;
import bgu.spl.net.common.Message;
import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BGRSEncoderDecoder implements MessageEncoderDecoder<Message> {
//    private final ObjectEncoderDecoder lineMessageEC = new ObjectEncoderDecoder();
    private  final LineMessageEncoderDecoder lineMessageEC = new LineMessageEncoderDecoder();

    @Override
    public Message decodeNextByte(byte nextByte) {
//        Serializable line = lineMessageEC.decodeNextByte(nextByte);
        String line = lineMessageEC.decodeNextByte(nextByte);
        if (line == null)
            return null;
        return parseString(line);
    }


    @Override
    public byte[] encode(Message message) {
        return message.serialize();
    }

    public Message parseString(String str){
        String[] tmp = str.split("\0");
        List<String> params = new LinkedList<>(Arrays.asList(tmp).subList(1, tmp.length));
        Message msg;
        short opCode=Short.parseShort(tmp[0]);
        switch (opCode){
            case 01:{
                msg=new RegistrationCommand(opCode, true);
            }
            break;
            default:
                msg= new Error((short)13, opCode);
        }
        return msg;
    }



}
