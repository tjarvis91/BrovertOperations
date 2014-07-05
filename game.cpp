#include "game.h"
#include <vector>

Game::Game( bool is_server )
{
	this.is_server = is_server;
}

void Game::add_mission_vote( int player_id, bool success )
{

}

void Game::add_player( int player_id )
{
	if (this.is_server)
	{
		this.player_list.insert(player_id);
	}
	else
	{
		//Tell server to add player
	}

}

Player Game::get_player(int player_id)
{
	return this.player_list[player_id];
}

void Game::add_selection_vote( int player_id, bool success )
{

}

void Game::make_selection( int player_id, vector<int> selection )
{

}

void Game::start()
{

}