package bgu.spl.net.commands;

import bgu.spl.net.impl.rci.Command;
import bgu.spl.net.srv.Database;

public abstract class BGRSCommand implements Command<Database> {
    short code;

}
