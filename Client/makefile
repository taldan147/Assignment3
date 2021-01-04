CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude 
LDFLAGS:=-lboost_system -lboost_thread -pthread

all: BGRSclient

BGRSclient: bin/connectionHandler.o bin/InputParser.o bin/SocketListener.o bin/Main.o
	@echo 'Building target: BGRSclient'
	g++ -o BGRSclient bin/connectionHandler.o bin/InputParser.o bin/SocketListener.o bin/Main.o  $(LDFLAGS)
	@echo 'Finished building target: BGRSclient'
	@echo ' '

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/connectionHandler.o src/connectionHandler.cpp

bin/InputParser.o: src/InputParser.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/InputParser.o src/InputParser.cpp

bin/SocketListener.o: src/SocketListener.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/SocketListener.o src/SocketListener.cpp

bin/Main.o: src/Main.cpp
	g++ -g -Wall -Weffc++ -std=c++11 -c -Iinclude -o bin/Main.o src/Main.cpp



clean:
	rm -f bin/*