//
// Created by spl211 on 29/12/2020.
//

#include "../include/InputParser.h"

InputParser::InputParser(int id, std::mutex &mutex, ConnectionHandler &handler, std::atomic<bool> &running) :
        _opCodes({}),_queries({5,6,7,9,10}),_id(id),_mutex(mutex),_handle(handler),_running(running) {
    _opCodes["ADMINREG"] = 1;
    _opCodes["STUDENTREG"] = 2;
    _opCodes["LOGIN"] = 3;
    _opCodes["LOGOUT"] = 4;
    _opCodes["COURSEREG"] = 5;
    _opCodes["KDAMCHECK"] = 6;
    _opCodes["COURSESTAT"] = 7;
    _opCodes["STUDENTSTAT"] = 8;
    _opCodes["ISREGISTERED"] = 9;
    _opCodes["UNREGISTER"] = 10;
    _opCodes["MYCOURSES"] = 11;
}


int InputParser::parse(std::string line, char bytes[]) {
    int diff;
    bool query = false;
    std::size_t pos = line.find_first_of(" ");
    diff = line.size() - pos + 2;
    std::string op = line.substr(0, pos);
    short code = _opCodes.at(op);
    line = line.substr(pos + 1);
    bytes[0] = ((code >> 8) & 0xFF);
    bytes[1] = (code & 0xFF);
    for (short queryCode:_queries) {
        if (code == queryCode) {
            query = true;
        }
    }
    if (query) {
        diff = 4;
        short queryTarget = boost::lexical_cast<short>(line);
        bytes[2] = ((queryTarget >> 8) & 0xFF);
        bytes[3] = (queryTarget & 0xFF);
    } else {
        for (unsigned int i = 2; i < line.size() + 2; i++) {
            bytes[i] = line[i - 2];
        }
        bytes[line.size() + 2] = '\0';
    }

    changeDelimiter(bytes, diff);
    return diff;
}

void InputParser::changeDelimiter(char charArr[], int length) {
    for (signed int i = 0; i < length; i++) {
        if (charArr[i] == ' ')
            charArr[i] = '\0';
    }
}

void InputParser::run() {
    while (_running) {
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
        std::string line(buf);
        int len = line.length();
        char bytes[len];
        int diff = parse(line, bytes);
        if (!_handle.sendBytes(bytes, diff)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        boost::this_thread::sleep(boost::posix_time::milliseconds(1000));
    }
}
