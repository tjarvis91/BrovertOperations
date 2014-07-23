package com.nickromito.brovalon.model;

import java.util.List;

/**
 * @author Justin
 *
 *	Represents a mission in a game of Brovalon
 */
public class Mission {
	public enum Status
	{
		PASSED,
		FAILED,
		NONE
	}
	
	public int playerCount; // Number of players going on this mission
	public List<Player> chosen; // List of players chosen for this mission
	public int voteRound; // Voting round this mission is in
	public Status status; // Status of this mission
	
	public Mission( int playerCount )
	{
		this.playerCount = playerCount;
		chosen = null;
		voteRound = 0;
		status = Status.NONE;
	}
	
	public boolean setChosen(List<Player> chosen) {
		// Check that the right number of players was picked
		if( chosen.size() != playerCount )
		{
			return false;
		}
		
		// Set the chosen list
		this.chosen = chosen;
		return true;
	}

}
