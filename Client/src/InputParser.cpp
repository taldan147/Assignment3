//
// Created by spl211 on 29/12/2020.
//

#include "../include/InputParser.h"

InputParser::InputParser(): _opCodes({}) {
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


//std::string InputParser::Parse(std::string line) {
//    std::size_t pos = line.find_first_of(" ");
//    std::string op = line.substr(0,pos);
//    std::string toReturn = _opCodes.at(op);
//    toReturn.push_back('\0');
//    line = line.substr(pos+1);
//    pos = line.find_first_of(" ");
//    toReturn.insert(toReturn.length(), line.substr(0,pos));
//    line = line.substr(pos+1);
//    toReturn.push_back('\0');
//    toReturn.insert(toReturn.length(),line);
//    return toReturn;
//}
int InputParser::parse(std::string line, char bytes[]) {
    int diff;
    std::size_t pos = line.find_first_of(" ");
    diff = line.size()-pos+3;
    std::string op = line.substr(0,pos);
    short code = _opCodes.at(op);
    line = line.substr(pos);
    bytes[0] = ((code >> 8) & 0xFF);
    bytes[1] = (code & 0xFF);
    for (unsigned int i = 2; i<line.size()+2; i++) {
        bytes[i] = line[i-2];
    }
    bytes[line.size()+2] = '\0';
    changeDelimiter(bytes, diff);
    return diff;
}

void InputParser::changeDelimiter(char charArr[], int length) {
    for ( signed int i=0; i< length; i++){
        if (charArr[i] == ' ')
            charArr[i] = '\0';
    }

}
