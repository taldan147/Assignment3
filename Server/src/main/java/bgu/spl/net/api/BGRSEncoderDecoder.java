package bgu.spl.net.api;

import bgu.spl.net.commands.LogInCommand;
import bgu.spl.net.commands.LogOutCommand;
import bgu.spl.net.commands.RegistrationCommand;
import bgu.spl.net.commands.Error;
import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.commands.base.Message;
import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BGRSEncoderDecoder implements MessageEncoderDecoder<Message> {
    private final OpCodeDecoder opCodeDecoder = new OpCodeDecoder();
    private final LineMessageEncoderDecoder lineDecoder = new LineMessageEncoderDecoder();
    private boolean messageReady = false;
    private LinkedList<String> params=new LinkedList<>();
    private Set<Integer> queries= new HashSet<Integer>(){{add(5);add(6);add(8);add(7);}};
    Short code = null;
    String param=null;
    Short queryCode=null;

    @Override
    public Message decodeNextByte(byte nextByte) {

        if (code == null) {
            code = opCodeDecoder.decodeNextByte(nextByte);
            return null;
        }
        if(queries.contains(code.intValue())){
            return decodeQuery(nextByte);
        }
        param = lineDecoder.decodeNextByte(nextByte);
        if (param == null)
            return null;
        else{
            params.add(param);
            param=null;
        }
        return parse(code);
    }

    private Message decodeQuery(byte nextByte) {

        if (queryCode == null) {
            queryCode = opCodeDecoder.decodeNextByte(nextByte);
            return null;
        }
        switch (code){
            case 5:
                return null;

            default:
                return null;
        }

    }
    private void reset(){
        params = new LinkedList<>();
        code = null;
        param=null;
        queryCode=null;
//        opCodeDecoder.resetFinished();
    }

    private Message parse(Short code) {
        Message msg;
        switch (code) {
            case 1: {
                if(params.size()<2){
                    return null;
                }
                msg = new RegistrationCommand(code, true, params);
                reset();
                break;
            }
            case 2: {
                if(params.size()<2){
                    return null;
                }
                msg = new RegistrationCommand(code, false, params);
                reset();
                break;
            }
            case 3:{
                if(params.size()<2){
                    return null;
                }
                msg = new LogInCommand(code, params);
                ClientMessage curr = (ClientMessage)msg;
                curr.setUsername(params.get(0));
                reset();
                break;
            }
            case 4:{
                msg = new LogOutCommand(code, params);
                reset();
                break;
            }
            default:
                msg = new Error(code, new LinkedList<>());
        }
        return msg;
    }


    @Override
    public byte[] encode(Message message) {
        return message.serialize();
    }

    public void readParams(int length, LinkedList<String> params) {

    }


}

