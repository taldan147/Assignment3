//
// Created by spl211 on 29/12/2020.
//

#include "../include/SocketListener.h"

SocketListener::SocketListener (int id, std::mutex& mutex, ConnectionHandler *handler): _id(id), _mutex(mutex), _handle(handler){}

void SocketListener::run(){
    while (1) {
        // We can use one of three options to read data from the server:
        // 1. Read a fixed number of characters
        // 2. Read a line (up to the newline character using the getline() buffered reader
        // 3. Read up to the null character
        std::string answer;
        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
        char bytes[4];



        if (!_handle->getBytes(bytes, 4)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        auto code = (short)((bytes[0] & 0xff) << 8);
        code += (short)(bytes[1] & 0xff);
        auto origcode= (short)((bytes[2] & 0xff) << 8);
        origcode += (short)(bytes[3] & 0xff);


        // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
        // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
        if (code == 12 && !_handle->getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }

        if(code==13){
            std::cout<<"ERROR "<<origcode << std::endl;
        }
        else{
            int len=answer.size();
            answer.resize(len - 1);
            std::cout  << "ACK " << origcode << " " << answer << std::endl;
        }



    }
}