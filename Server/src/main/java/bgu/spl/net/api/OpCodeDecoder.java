package bgu.spl.net.api;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class OpCodeDecoder implements MessageEncoderDecoder<Short> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private int finished=0;

    @Override
    public Short decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow us to do the following comparison
        pushByte(nextByte);
        
        if (finished>=2) {
            return popCode();
        }


        return null; //not a line yet
    }

    @Override
    public byte[] encode(Short message) {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((message >> 8) & 0xFF);
        bytesArr[1] = (byte)(message & 0xFF);
        return bytesArr;
    }


    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }
        finished++;
        bytes[len++] = nextByte;
    }

    private short popCode() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        short result = (short)((bytes[0] & 0xff) << 8);
        result += (short)(bytes[1] & 0xff);
        len =0;
        finished=0;
        return result;
    }
//    public void resetFinished(){ finished=0;}
}


