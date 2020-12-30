//
// Created by spl211 on 29/12/2020.
//

#include "../include/InputParser.h"

InputParser::InputParser(): _opCodes({}) {
    _opCodes["ADMINREG"] = "01";
    _opCodes["STUDENTREG"] = "02";
    _opCodes["LOGIN"] = "03";
    _opCodes["LOGOUT"] = "04";
    _opCodes["COURSEREG"] = "05";
    _opCodes["KDAMCHECK"] = "06";
    _opCodes["COURSESTAT"] = "07";
    _opCodes["STUDENTSTAT"] = "08";
    _opCodes["ISREGISTERED"] = "09";
    _opCodes["UNREGISTER"] = "10";
    _opCodes["MYCOURSES"] = "11";


}

std::string InputParser::Parse(std::string line) {
    std::size_t pos = line.find_first_of(" ");
    std::string op = line.substr(0,pos);

    return std::__cxx11::string();
}
