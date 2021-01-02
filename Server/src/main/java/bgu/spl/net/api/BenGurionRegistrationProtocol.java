package bgu.spl.net.api;

import bgu.spl.net.commands.LoginCommand;
import bgu.spl.net.commands.RegistrationCommand;
import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.commands.Error;
import bgu.spl.net.commands.base.Message;
import bgu.spl.net.srv.Database;

import java.util.LinkedList;

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
            if (!loggedIn && !(message instanceof RegistrationCommand)) {
                return new Error(message.getOpcode(), new LinkedList<String>());
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
