package bgu.spl.net.api;

import bgu.spl.net.commands.BGRSCommand;
import bgu.spl.net.impl.rci.Command;
import bgu.spl.net.srv.Database;

public class BenGurionRegistrationProtocol implements MessagingProtocol<BGRSCommand>{

    @Override
    public BGRSCommand process(BGRSCommand msg) {
        return null;
    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }
}
