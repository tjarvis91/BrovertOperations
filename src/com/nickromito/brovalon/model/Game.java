package com.nickromito.brovalon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Justin
 *
 * Represents a Brovalon game instance.
 */
public class Game {
	
	public enum State
	{
		NOT_STARTED,
		SELECTION,
		VOTING,
		MISSION,
		FINISH
	}
	
	public Map<Integer,Player> playerMap;
	public List<Mission> missionList;
	public int nextUid = 0;
	public State state;
	public int currentMission;
	public int crownPlayer;
	
	public Game()
	{
		playerMap = new HashMap<Integer, Player>();
		missionList = new ArrayList<Mission>();
		state = State.NOT_STARTED;
		currentMission = 0;
		crownPlayer = 0;
	}
	
}
