//
// Created by spl211 on 29/12/2020.
//

#include "../include/SocketListener.h"

SocketListener::SocketListener(int id, std::mutex &mutex, ConnectionHandler &handler, std::atomic<bool>& running) : _id(id), _mutex(mutex),
                                                                                        _handle(handler),
                                                                                        _running(running) {}

void SocketListener::run() {
    while (1) {
        // We can use one of three options to read data from the server:
        // 1. Read a fixed number of characters
        // 2. Read a line (up to the newline character using the getline() buffered reader
        // 3. Read up to the null character
        std::string answer;
        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
        char bytes[4];
        if (!_handle.getBytes(bytes, 4)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            _mutex.lock();
            _running = false;
            _mutex.unlock();
            break;
        }
        auto code = (short) ((bytes[0] & 0xff) << 8);
        code += (short) (bytes[1] & 0xff);
        auto origcode = (short) ((bytes[2] & 0xff) << 8);
        origcode += (short) (bytes[3] & 0xff);
        if (code == 12 && !_handle.getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            _mutex.lock();
            _running = false;
            _mutex.unlock();
            break;
        }

        if (code == 13) {
            std::cout << "ERROR " << origcode << std::endl;
        } else {
            int len = answer.size();
            answer.resize(len - 1);
            std::cout << "ACK " << origcode << answer << std::endl;
            if(origcode==4){
                std::cout << "Logged out successfully. Have a nice day :):):)...\n" << std::endl;
                _mutex.lock();
                _running = false;
                _mutex.unlock();
                break;
            }
        }


    }
}

bool SocketListener::isRunning() {
    return _running;
}
