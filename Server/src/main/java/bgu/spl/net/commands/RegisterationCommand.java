package bgu.spl.net.commands;

import bgu.spl.net.common.Admin;
import bgu.spl.net.common.Message;
import bgu.spl.net.common.Student;
import bgu.spl.net.common.User;
import bgu.spl.net.srv.Database;
import com.sun.xml.internal.ws.api.client.SelectOptimalEncodingFeature;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

public class RegisterationCommand extends BGRSCommand {
    private boolean isAdmin;

    public RegisterationCommand(short opcode, boolean isAdmin){
        super(opcode);
        this.isAdmin = isAdmin;
    }
    @Override
    public Serializable execute(Message arg) {
        String username =  arg.getParameters().get(0);
        String password = arg.getParameters().get(1);
        Database database = Database.getInstance();
        if (database.doesUserExists(username))
            return new Message((short)13, new LinkedList<String>(Arrays.asList(String.valueOf(code))));
        User toRegister;
        if (isAdmin)
             toRegister = new Admin(username,password);
        else
            toRegister = new Student(username,password);
        database.registerUser(toRegister);
        return new Message((short)12, new LinkedList<String>(Arrays.asList(String.valueOf(code),"\0")));
    }

}
