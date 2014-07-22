package teamname.brovalon.model;

/**
 * @author Justin
 *
 * Represents a player in a Brovalon game
 */
public class Player {

	public enum Role
	{
		RESISTANCE,
		SPY,
		NONE
	}
	
	public enum AcceptReject
	{
		ACCEPT,
		REJECT,
		NONE
	}
	
	public enum SuccessFail
	{
		SUCCESS,
		FAIL,
		NONE
	}
	
	public static final int UID_INVALID = 0xFFFFFFFF;
	
	public String displayName;			// Name to be shown in-game
	public int uid;						// Unique player identifier assigned by game
	public AcceptReject acceptReject;	// Current accept/reject status
	public SuccessFail successFail; 	// Current success/fail status
	public int selection[];				// Current mission selection
	public boolean ready;				// Ready to start the game
	public Role role;					// Player's role
	transient public Game game;			// Current game
	transient public Com com;			// Communications service
	
	public Player( String displayName )
	{
		this.displayName = displayName;
		this.uid = UID_INVALID;
		this.acceptReject = AcceptReject.NONE;
		this.successFail = SuccessFail.NONE;
		this.selection = null;
		this.game = null;
		this.com = null;
		this.ready = false;
		this.role = Role.NONE;
	}

	/**
	 * Copies transferable information from the newer player into
	 * this one.
	 * @param newer
	 */
	public void update( Player newer )
	{
		this.acceptReject = newer.acceptReject;
		this.successFail = newer.successFail;
		this.selection = newer.selection;
		this.ready = newer.ready;
	}
	
}
