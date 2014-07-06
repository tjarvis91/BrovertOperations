#include "player.h"

Player::Player( int id, Role role )
{
	// Set id
	this->id = id;

	// Set role
	this->role = role;

	// Set team based on role
	switch ( role )
	{
	case Assassin:
	case Lancelot_B2G:
	case Minion:
	case Mordred:
	case Morgana:
		this->team = BAD_GUYS;
		break;
	case Lancelot_G2B:
	case Merlin:
	case Percival:
	case Servant:
		this->team = GOOD_GUYS;
		break;
	default:
		cout << "HIT A DEFAULT CASE ERROR ERROR STAHP!";
	}
}

Player::~Player()
{

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

Team Player::get_team()
{
	return this->team;
}

bool Player::switch_team()
{
	bool ret = true;

	if( this->role == Lancelot_B2G )
	{
		this->team = GOOD_GUYS;
	}
	else if( this->role == Lancelot_G2B )
	{
		this->team = BAD_GUYS;
	}
	else
	{
		cout << "WTF ARE YOU DOING STAHP! ERROR ERROR DANGER WILL ROBINSON!";
		ret = false;
	}

	return( ret );
}