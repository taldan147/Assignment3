package bgu.spl.net.api;

import bgu.spl.net.commands.*;

import bgu.spl.net.commands.Error;
import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.commands.base.Message;
import bgu.spl.net.srv.Database;

import java.util.LinkedList;

public class BenGurionRegistrationProtocol implements MessagingProtocol<Message> {
    private String userName;
    private boolean loggedIn;
    private boolean shouldTerminate = false;

    @Override
    public Message process(Message msg) {
        Message com;
        if (msg instanceof Error) {
            return msg;
        }
        ClientMessage message = (ClientMessage) msg;
        if ((!loggedIn && !(message instanceof RegistrationCommand) && !(message instanceof LogInCommand))
                || (loggedIn && (message instanceof RegistrationCommand || message instanceof LogInCommand))) {
            return new Error(message.getOpcode(), new LinkedList<String>());
        }
        if (message instanceof LogInCommand) {
            userName = (message).getUsername();
            Message response = (Message) message.execute(Database.getInstance());
            if (response instanceof Ack)
                loggedIn = true;
            return response;
        } else if (message instanceof LogOutCommand) {
            loggedIn = false;
            message.setUsername(userName);
            shouldTerminate = true;
        }
        message.setUsername(userName);
        return (Message) message.execute(Database.getInstance());
    }


    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
