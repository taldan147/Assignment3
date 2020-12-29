package bgu.spl.net.commands;

import bgu.spl.net.common.Message;
import bgu.spl.net.srv.Database;

import java.io.Serializable;

public class RegisterationCommand extends BGRSCommand {
    private boolean isAdmin;

    @Override
    public Serializable execute(Message arg) {
        return null;
    }

}
