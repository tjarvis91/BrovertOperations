package teamname.brovalon.test;

import java.util.ArrayList;
import java.util.List;

import teamname.brovalon.model.Game;
import teamname.brovalon.model.HostGame;
import teamname.brovalon.model.Mission;
import teamname.brovalon.model.MyPlayer;
import teamname.brovalon.model.Player;
import teamname.brovalon.model.Com.ComType;
import junit.framework.TestCase;

public class TestMyPlayer extends TestCase {

	/**
	 * Yay testing!
	 */
	public void testJoinGame()
	{
		Game game = new HostGame();
		MyPlayer myPlayer = new MyPlayer( "justin" );
		
		myPlayer.setCom( ComType.LOCAL );
		myPlayer.joinGame(game);
		
		assertEquals( 1, game.playerMap.size() );
		assertEquals( "justin", game.playerMap.get(0).displayName );
		assertNotSame( Player.UID_INVALID, game.playerMap.get(0).uid );
	}
	
	public void testAcceptSelection()
	{
		Game game = new HostGame();
		MyPlayer myPlayer = new MyPlayer( "justin" );
		
		myPlayer.setCom( ComType.LOCAL );
		myPlayer.joinGame(game);
		
		myPlayer.acceptSelection();
		
		assertEquals( Player.AcceptReject.ACCEPT, game.playerMap.get(0).acceptReject );
	}
	
	public void testStartGame()
	{
		Game game = new HostGame();
		List<MyPlayer> playerList = new ArrayList<MyPlayer>();
		for( int i=0; i<5; i++ )
		{
			playerList.add( new MyPlayer( Integer.toString(i) ) );
			playerList.get(i).setCom( ComType.LOCAL );
			playerList.get(i).joinGame(game);
			playerList.get(i).startGame();
		}
		
		// Verify that the game started
		assertEquals( Game.State.SELECTION, game.state );
		assertEquals( 0, game.crownPlayer );
	}
	
	public void testMakeSelection()
	{
		Game game = new HostGame();
		List<MyPlayer> playerList = new ArrayList<MyPlayer>();
		for( int i=0; i<5; i++ )
		{
			playerList.add( new MyPlayer( Integer.toString(i) ) );
			playerList.get(i).setCom( ComType.LOCAL );
			playerList.get(i).joinGame(game);
			playerList.get(i).startGame();
		}
		
		MyPlayer crownPlayer = playerList.get(0);
		int selection[] = new int[2];
		selection[0] = 0;
		selection[1] = 1;
		crownPlayer.makeSelection( selection );
		
		// Verify that the game moved to the voting state
		assertEquals( Game.State.VOTING, game.state );
		// TODO: Add tests for invalid team selection
	}
	
	public void testRejectSelection()
	{
		Game game = new HostGame();
		List<MyPlayer> playerList = new ArrayList<MyPlayer>();
		for( int i=0; i<5; i++ )
		{
			playerList.add( new MyPlayer( Integer.toString(i) ) );
			playerList.get(i).setCom( ComType.LOCAL );
			playerList.get(i).joinGame(game);
			playerList.get(i).startGame();
		}
		
		MyPlayer crownPlayer = playerList.get(0);
		int selection[] = new int[2];
		selection[0] = 0;
		selection[1] = 1;
		crownPlayer.makeSelection( selection );
		
		// All players reject
		for( MyPlayer p : playerList )
		{
			p.rejectSelection();
		}
		
		// Verify that the mission moved to the selection next round when rejected
		assertEquals( Game.State.SELECTION, game.state );
		assertEquals( 1, game.crownPlayer );
		assertEquals( 1, game.missionList.get(0).voteRound );
		
		// Verify that the player's votes and selection were cleared
		assertEquals( crownPlayer.acceptReject, Player.AcceptReject.NONE );
		assertEquals( null, crownPlayer.selection );
		
		// All players reject 4 more times
		for( int i=0; i<4; i++ )
		{
			for( MyPlayer p : playerList )
			{
				p.rejectSelection();
			}
		}
		
		// Verify that the mission was failed
		assertEquals( Mission.Status.FAILED, game.missionList.get(0).status );
		assertEquals( 0, game.crownPlayer );
		assertEquals( 1, game.currentMission );
		assertEquals( 0, game.missionList.get( game.currentMission ).voteRound );
	}
	
}
