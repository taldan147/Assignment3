//
// Created by spl211 on 29/12/2020.
//

#ifndef CLIENT_SOCKETLISTENER_H
#define CLIENT_SOCKETLISTENER_H


#include <mutex>
#include "../include/connectionHandler.h"


class SocketListener {
private:
    int _id;
    std::mutex & _mutex;
    ConnectionHandler* _handle;
public:


    void run();

    SocketListener(int id, std::mutex& mutex, ConnectionHandler *handler);
};


#endif //CLIENT_SOCKETLISTENER_H
