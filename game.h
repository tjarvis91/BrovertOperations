#pragma once

#include "player.h"
#include <vector>

using namespace std;

class Game {
public:
	//
	Game( bool is_server );			
						// Constructor
	void add_mission_vote( int player_id, bool success );
	void add_player( int player_id );
	void add_selection_vote( int player_id, bool success );
	int get_crown();
	Player get_player( int player_id );
	void make_selection( int player_id, vector<int> * selection );
	void start();
private:
	bool is_server;		// Is this game instance a server?
	vector<Player> player_list; // List of players in the game
};

