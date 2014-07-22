package teamname.brovalon.model;

public class LocalCom implements Com {

	@Override
	public void sendPlayerUpdate(Player src, Game dest) {
		HostGame hostGame = (HostGame)dest;
		hostGame.updatePlayer( src );
	}

	@Override
	public void sendGameUpdate(Game src, Player dest) {
		// TODO Auto-generated method stub

	}

}
