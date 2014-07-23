/**
 * 
 */
package com.nickromito.brovalon.model;

/**
 * @author Justin
 * Interface for Brovalon communications
 */
public interface Com {
	
	enum ComType
	{
		LOCAL
	}
	
	/**
	 * Sends the updated information for the source player to the destination game.
	 * This is how players perform actions.
	 * @param src
	 * @param dest
	 */
	public void sendPlayerUpdate( Player src, Game dest );
	/**
	 * Sends the updated information for the source game to the destination player.
	 * This is how players observe game state changes.
	 * @param src
	 * @param dest
	 */
	public void sendGameUpdate( Game src, Player dest );
	
}
