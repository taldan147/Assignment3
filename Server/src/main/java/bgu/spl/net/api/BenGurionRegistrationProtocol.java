package bgu.spl.net.api;

import bgu.spl.net.commands.LoginCommand;
import bgu.spl.net.commands.RegistrationCommand;
import bgu.spl.net.common.ClientMessage;
import bgu.spl.net.common.Error;
import bgu.spl.net.common.Message;
import bgu.spl.net.srv.Database;

import java.io.Serializable;

public class BenGurionRegistrationProtocol implements MessagingProtocol<Message> {
    private String userName;
    private boolean loggedIn;

    @Override
    public Message process(Message msg) {
        Message com;
        ClientMessage message = (ClientMessage) msg;
        if (message instanceof LoginCommand) {
            userName = (message).getUsername();
            loggedIn = true;
        } else {
            if (!loggedIn) {
                return new Error((short) 13, message.getOpcode());
            }
            message.setUsername(userName);
        }
        return (Message) message.execute(Database.getInstance());
    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }
}
