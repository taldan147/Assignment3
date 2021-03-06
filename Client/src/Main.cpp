
#include "../include/Main.h"

int main(int argc, char *argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl <<
                  std::endl;
        return -1;
    }
    std::string host = argv[1];
    short port = atoi(argv[2]);

    ConnectionHandler connectionHandler(host, port);

    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port <<std::endl;
        return 1;
    }
    std::mutex mutex;

    std::atomic<bool> _running(true);

    SocketListener listener(1, mutex, connectionHandler, _running);
    InputParser parser(1, mutex, connectionHandler, _running);

    boost::thread userInput (&InputParser::run,&parser);
    boost::thread server (&SocketListener::run, &listener);

    while(listener.isRunning()){
        sleep(1);
    }

    userInput.join();
    return 0;
    }

