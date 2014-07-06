#pragma once

#include <cstring>
#include <cstdlib>
#include <iostream>
#include "types.h"

using namespace std;

enum Team
{
	BAD_GUYS = 0,
	GOOD_GUYS
};

//I'm a player, I'm a player - Tech N9ne
class Player
{
public:
	Player( int, Role );	// constructor
	Player( int );
	~Player();
	int get_id();
	Role get_role();
	Team get_team();
	bool switch_team();

private:
	int id;
	Role role;
	Team team;				// 1 for good guys, 0 for bad guys
	int status;				// placeholder for that some enum will probably go to eventually
 };