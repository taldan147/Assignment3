package bgu.spl.net.commands;

import bgu.spl.net.commands.base.ClientMessage;
import bgu.spl.net.common.*;
import bgu.spl.net.srv.Database;
//import com.sun.xml.internal.ws.api.client.SelectOptimalEncodingFeature;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RegistrationCommand extends ClientMessage {
    private boolean isAdmin;

    public RegistrationCommand(short opcode, boolean isAdmin, List<String> params){
        super(opcode, params);
        this.isAdmin = isAdmin;
    }
    @Override
    public Serializable execute(Database arg) {
        String username = parameters.get(0);
        String password = parameters.get(1);
        if (arg.registerUser(username, password, isAdmin))
            return new Ack(opcode, new LinkedList<>());
        return new Error(opcode, new LinkedList<>());
    }


}
