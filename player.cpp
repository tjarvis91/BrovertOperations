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
	case Lancelot_B2G:
	case Minion:
	case Mordred:
	case Morgana:
		this->team = 0;
	case Lancelot_G2B:
	case Merlin:
	case Percival:
	case Servant:
		this->team = 1;
	}
}

int Player::get_id()
{
	return this->id;
}

Role Player::get_role()
{
	return this->role;
}


Player::Player(int id)
{
	this->id = id;
}

int Player::get_id()
{
	return this->id;
}

bool Player::get_team()
{
	return this->role;
}

int Player::switch_team()
{
	this->role == Lancelot_B2G || this->role == Lancelot_G2B ? this->team = ( this->team + 1 ) % 2 : printf( "The Rock says, the Rock says know your damn role" );

}