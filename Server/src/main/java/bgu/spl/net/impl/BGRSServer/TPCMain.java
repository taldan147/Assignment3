package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.BGRSEncoderDecoder;
import bgu.spl.net.api.BenGurionRegistrationProtocol;
import bgu.spl.net.srv.Database;
import bgu.spl.net.srv.Server;

public class TPCMain {
    public static void main(String[] args) {
        Database.getInstance().initialize("src/main/Courses.txt");
        Server.threadPerClient(
                Integer.parseInt(args[0]), //port
                BenGurionRegistrationProtocol::new, //protocol factory
                BGRSEncoderDecoder::new //message encoder decoder factory
        ).serve();
    }
}