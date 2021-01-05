//
// Created by spl211 on 29/12/2020.
//

#ifndef CLIENT_INPUTPARSER_H
#define CLIENT_INPUTPARSER_H

#include <vector>
#include <string>
#include <unordered_map>
#include <boost/lexical_cast.hpp>
#include <mutex>
#include "../include/connectionHandler.h"
#include <boost/thread.hpp>




class InputParser {
private:
    std::unordered_map<std::string,short> _opCodes;
    std::vector<short> _queries;
    int _id;
    std::mutex & _mutex;
    ConnectionHandler& _handle;
    std::atomic<bool>& _running;
public:
    InputParser(int id, std::mutex& mutex, ConnectionHandler &handler, std::atomic<bool> &running);
//    std::string Parse(std::string line);
    int parse(std::string line, char bytes[]);
    void changeDelimiter(char charArr[], int len);

    void run();
};


#endif //CLIENT_INPUTPARSER_H
