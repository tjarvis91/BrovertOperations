#include <cstring>
#include <cstdlib>
#include <iostream>
#include "types.h"

using namespace std;

class Player
{
public:
	Player( int, Role );	// constructor
	Player(int);
	int get_id();
	string get_role();
	bool get_team();

private:
	int id;
	Role role;
	bool team;				// 1 for good guys, 0 for bad guys
	int status;				// placeholder for that some enum will probably go to eventually

};