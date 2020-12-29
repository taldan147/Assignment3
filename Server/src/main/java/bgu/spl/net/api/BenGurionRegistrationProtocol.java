package bgu.spl.net.api;

import bgu.spl.net.commands.BGRSCommand;
import bgu.spl.net.common.Message;
import bgu.spl.net.impl.rci.Command;
import bgu.spl.net.srv.Database;

public class BenGurionRegistrationProtocol implements MessagingProtocol<Message>
{


    @Override
    public Message process(Message msg) {
        return null;
    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }
}
