CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system -pthread

all: EchoClient
	g++ -o bin/echoExample bin/connectionHandler.o bin/echoClient.o bin/SocketListener.o bin/InputParser.o $(LDFLAGS)

EchoClient: bin/connectionHandler.o bin/echoClient.o bin/SocketListener.o bin/InputParser.o

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/echoClient.o: src/echoClient.cpp
	g++ $(CFLAGS) -o bin/echoClient.o src/echoClient.cpp

bin/SocketListener.o: src/SocketListener.cpp
	g++ $(CFLAGS) -o bin/SocketListener.o src/SocketListener.cpp

bin/InputParser.o: src/InputParser.cpp
	g++ $(CFLAGS) -o bin/InputParser.o src/InputParser.cpp

.PHONY: clean
clean:
	rm -f bin/*
