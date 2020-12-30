//
// Created by spl211 on 29/12/2020.
//

#ifndef CLIENT_INPUTPARSER_H
#define CLIENT_INPUTPARSER_H

#include <vector>
#include <string>
#include <unordered_map>


class InputParser {
private:
    std::unordered_map<std::string,std::string> _opCodes;
public:
    InputParser();
    std::string Parse(std::string line);
};


#endif //CLIENT_INPUTPARSER_H
