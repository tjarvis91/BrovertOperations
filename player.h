#pragma once

#include <cstring>
#include <cstdlib>
#include <iostream>

#include "game.h"

using namespace std;

//I'm a player, I'm a player - Tech N9ne
class Player
{
public:
	Player( int, Role );	// constructor
	int get_id();
	Role get_role();
	bool get_team();
	int switch_team();

private:
	int id;
	Role role;
	bool team;				// 1 for good guys, 0 for bad guys
	int status;				// placeholder for that some enum will probably go to eventually

};