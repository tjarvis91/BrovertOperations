package com.nickromito.brovalon.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Justin
 * The host for a Brovalon game. Provides functionality to change
 * game state based on player updates.
 */
public class HostGame extends Game {
	private static final int spyMap[] = { 2, 2, 3, 3, 3, 4 };
	private static final int[][] missionCountArray = 
		{
		// TODO: these aren't right
		{ 2, 3, 3, 4, 3 },
		{ 2, 3, 3, 4, 3 },
		{ 2, 3, 3, 4, 3 },
		{ 2, 3, 3, 4, 3 },
		{ 2, 3, 3, 4, 3 },
		{ 2, 3, 3, 4, 3 }
		};
	
	
	/**
	 * Updates the game with the provided player information,
	 * adding the player to the game if it doesn't exist
	 * 
	 * @param src - the player to update
	 */
	public void updatePlayer( Player src )
	{
		// Adding a new player
		if( src.uid == Player.UID_INVALID )
		{
			src.uid = nextUid++;
			playerMap.put( src.uid, src );
		}
		// Updating an existing player
		else if( playerMap.containsKey( src.uid ) )
		{
			Player toUpdate = playerMap.get( src.uid );
			// Check if the update contains a valid state
			if( !isValidUpdate( src, toUpdate ) )
			{
				return;
			}
			
			// Copy the new information into the player
			toUpdate.update( src );
			
			// Perform any needed game state changes
			updateState();
			
		}
	}
	
	
	/**
	 * Updates the game with the available player information,
	 * changing state if needed
	 */
	private void updateState() {
		switch( state )
		{
			case NOT_STARTED:
				// Check for enough players
				if( playerMap.size() < 5 )
				{
					return;
				}
				
				// Check for all players ready
				for( Player p : playerMap.values() )
				{
					if( !p.ready )
					{
						return;
					}
				}
				
				// Ready to start game!
				start();
				super.state = Game.State.SELECTION;
				break;
				
			case SELECTION:
				super.state = Game.State.VOTING;
				break;
				
			case VOTING:
				// Check all players' votes
				int acceptCount = 0;
				for( Player p : playerMap.values() )
				{
					if( p.acceptReject == Player.AcceptReject.NONE )
					{
						return;
					}
					else if( p.acceptReject == Player.AcceptReject.ACCEPT )
					{
						acceptCount++;
					}
				}
				
				// All players have voted
				Mission mission = missionList.get( currentMission );
				
				if( acceptCount > ( mission.playerCount / 2 ) )
				{
					// Accepted
					// Ready to send the mission
					super.state = Game.State.MISSION;
				}
				else
				{
					// Rejected
					mission.voteRound++;
					state = Game.State.SELECTION;
					passCrown();
					if( mission.voteRound >= 5 )
					{
						// Failed due to reject
						mission.status = Mission.Status.FAILED;
						currentMission++;
					}
				}			
				
				// Clear votes and selection for next round
				for( Player p : playerMap.values() )
				{
					// TODO: Implement game->player update scheme
					p.acceptReject = Player.AcceptReject.NONE;
					p.selection = null;
				}
				
				break;
				
			default:
				break;
		}
		
		// Send out the new game info to all players
		
	}
	
	
	/**
	 * Validates that the information contained in the src Player
	 * is a valid update to the player in the current game.
	 * @param src
	 * @param player
	 * @return
	 */
	private boolean isValidUpdate(Player src, Player player) {
			// TODO Auto-generated method stub
			return true;
		}

	/**
	 * Starts the game, initializing game data
	 */
	private void start() {
		int numPlayers = playerMap.size();
		int numSpies = spyMap[ numPlayers ];
		Random random = new Random();
		
		// Assign player roles
		List<Player> playerList = new ArrayList<Player>( playerMap.values() );
		
		// Spies
		for( int i = 0; i < numSpies; i++ )
		{
			int spy = random.nextInt( playerList.size() );
			playerList.get( spy ).role = Player.Role.SPY;
			playerList.remove( spy );
		}
		
		// Resistance
		for( Player p : playerList )
		{
			p.role = Player.Role.RESISTANCE;
		}
		
		// Assign the crown
		crownPlayer = 0;
		
		// Initialize the missions
		int missionCounts[] = missionCountArray[ numPlayers ];
		for( int i = 0; i < 5; i++ )
			{
				missionList.add( new Mission( missionCounts[i] ) );
			}
		
	}
	
	
	/**
	 * Passes the crown to the next player
	 */
	private void passCrown()
	{
		crownPlayer = ( crownPlayer + 1 ) % playerMap.size();
	}
}
