package bgu.spl.net.api;

import bgu.spl.net.common.Message;
import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BGRSEncoderDecoder implements MessageEncoderDecoder<Message> {
    private final LineMessageEncoderDecoder lineMessageEC = new LineMessageEncoderDecoder();

    @Override
    public Message decodeNextByte(byte nextByte) {
        String line = lineMessageEC.decodeNextByte(nextByte);
        return parseString(line);
    }

    @Override
    public byte[] encode(Message message) {
        return new byte[0];
    }

    public Message parseString(String str){
        String[] tmp = str.split("\0");
        List<String> params = new LinkedList<>(Arrays.asList(tmp).subList(1, tmp.length));
        return new Message(Short.parseShort(tmp[0]),params);
    }

}
