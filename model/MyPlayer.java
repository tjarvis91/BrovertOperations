package teamname.brovalon.model;

/**
 * @author Justin
 * Player class for active player. Provides all player actions.
 * Most player actions simply set the local value for the player, then call
 * the update function to send the action to the host game.
 */
public class MyPlayer extends Player {
	
	public MyPlayer( String displayName )
	{
		super( displayName );
	}
	
	public void setCom( Com.ComType comType )
	{
		// only local supported currently
		super.com = new LocalCom();
	}
	
	public void joinGame( Game game )
	{
		super.game = game;
		sendUpdate();
	}
	
	public void startGame()
	{
		this.ready = true;
		sendUpdate();
	}
	
	public void acceptSelection()
	{
		super.acceptReject = AcceptReject.ACCEPT;
		sendUpdate();
	}
	
	public void makeSelection( int uid[] )
	{
		super.selection = uid;
		sendUpdate();
	}
	
	public void rejectSelection()
	{
		super.acceptReject = AcceptReject.REJECT;
		sendUpdate();
	}
	
	public void throwSuccess()
	{
		super.successFail = SuccessFail.SUCCESS;
		sendUpdate();
	}
	
	public void throwFail()
	{
		super.successFail = SuccessFail.FAIL;
		sendUpdate();
	}
	
	/**
	 * Sends an update of this player's information to the game.
	 */
	public void sendUpdate()
	{
		com.sendPlayerUpdate( this, this.game );
	}
}
