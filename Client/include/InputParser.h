//
// Created by spl211 on 29/12/2020.
//

#ifndef CLIENT_INPUTPARSER_H
#define CLIENT_INPUTPARSER_H

#include <vector>
#include <string>


class InputParser {
private:
    std::vector<std::pair<int,std::string>> _opCodes;
public:
    InputParser();
    std::string Parse(std::string line);
};


#endif //CLIENT_INPUTPARSER_H
