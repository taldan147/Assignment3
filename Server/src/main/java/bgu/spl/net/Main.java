package bgu.spl.net;

import bgu.spl.net.api.BGRSEncoderDecoder;
import bgu.spl.net.api.BenGurionRegistrationProtocol;
import bgu.spl.net.impl.echo.EchoProtocol;
import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;
import bgu.spl.net.impl.newsfeed.NewsFeed;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;
import bgu.spl.net.srv.Server;

public class Main {
    public static void main(String[] args) {
        NewsFeed feed = new NewsFeed(); //one shared object

// you can use any server...
//        Server.threadPerClient(
//                7777, //port
//                () -> new RemoteCommandInvocationProtocol<>(feed), //protocol factory
//                ObjectEncoderDecoder::new //message encoder decoder factory
//        ).serve();

        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                7777, //port
                EchoProtocol::new, //protocol factory
                LineMessageEncoderDecoder::new //message encoder decoder factory
        ).serve();
    }
}
