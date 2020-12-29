package bgu.spl.net.common;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    short opcode;
    List<String> parameters;
}
