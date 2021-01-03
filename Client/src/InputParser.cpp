//
// Created by spl211 on 29/12/2020.
//

#include "../include/InputParser.h"

InputParser::InputParser() : _opCodes({}), _queries({5, 6, 7, 9, 10}) {
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
    bool query=false;
    std::size_t pos = line.find_first_of(" ");
    diff = line.size() - pos + 2;
    std::string op = line.substr(0, pos);
    short code = _opCodes.at(op);
    line = line.substr(pos + 1);
    bytes[0] = ((code >> 8) & 0xFF);
    bytes[1] = (code & 0xFF);
    for (short queryCode:_queries) {
        if (code == queryCode) {
            query=true;
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
