#include "player.h"

Player::Player( int id, Role role )
{
	// Set id
	this->id	= id;

	// Set role
	this->role	= role;

	// Set team based on role
	switch ( role )
	{
	case Assassin:
	case Minion:
	case Mordred:
	case Morgana:
		this->team = 0;
	case Merlin:
	case Percival:
	case Servant:
		this->team = 1;
	}



}

Player::Player(int id)
{
	this->id = id;
}

int Player::get_id()
{
	return this->id;
}