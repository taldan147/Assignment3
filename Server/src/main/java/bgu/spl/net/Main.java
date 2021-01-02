package bgu.spl.net;

import bgu.spl.net.api.BGRSEncoderDecoder;
import bgu.spl.net.api.BenGurionRegistrationProtocol;
import bgu.spl.net.srv.Database;
import bgu.spl.net.srv.Server;

public class Main {
    public static void main(String[] args) {
        Database.getInstance().initialize("src/main/Courses.txt");

// you can use any server...
//        Server.threadPerClient(
//                7777, //port
//                () -> new RemoteCommandInvocationProtocol<>(feed), //protocol factory
//                ObjectEncoderDecoder::new //message encoder decoder factory
//        ).serve();

        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                7777, //port
                BenGurionRegistrationProtocol::new, //protocol factory
                BGRSEncoderDecoder::new //message encoder decoder factory
        ).serve();
//        Server.reactor(
//                Runtime.getRuntime().availableProcessors(),
//                7777, //port
//                EchoProtocol::new, //protocol factory
//                LineMessageEncoderDecoder::new //message encoder decoder factory
//        ).serve();
    }
}
