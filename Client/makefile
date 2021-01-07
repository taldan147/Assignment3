CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude 
LDFLAGS:=-lboost_system -lboost_thread -pthread

all: BGRSclient
	g++ -o bin/BGRSclient  bin/connectionHandler.o bin/SocketListener.o bin/InputParser.o bin/Main.o $(LDFLAGS)

BGRSclient: bin/connectionHandler.o bin/SocketListener.o bin/InputParser.o bin/Main.o

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp
bin/SocketListener.o: src/SocketListener.cpp
	g++ $(CFLAGS) -o bin/SocketListener.o src/SocketListener.cpp
bin/InputParser.o: src/InputParser.cpp
	g++ $(CFLAGS) -o bin/InputParser.o src/InputParser.cpp
bin/Main.o: src/Main.cpp
	g++ $(CFLAGS) -o bin/Main.o src/Main.cpp
.PHONY: clean
clean:
	rm -f bin/*