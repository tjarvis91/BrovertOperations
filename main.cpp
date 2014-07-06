# include "Iw2D.h"
#include "game.h"

int main()
{
	static const int arr[] = { 1, 3, 4 };
	// Initialise the 2D graphics system
	Iw2DInit();

	// Model test
	Game game = Game( true );
	
	// Add players
	game.add_player( 0 );
	game.add_player( 1 );
	game.add_player( 2 );
	game.add_player( 3 );
	game.add_player( 4 );

	// Start the game
	game.start();

	// Get players
	vector< Player > players;
	for( int i=0; i<5; i++ )
	{
		players.push_back( game.get_player( i ) );
	}

	// Make selection
	int crown_player_id = game.get_crown();
	game.make_selection( crown_player_id, new vector<int>( arr, arr + sizeof( arr ) / sizeof( arr[0] ) ) );

	// Make votes
	for( int i=0; i<5; i++ )
	{
		game.add_selection_vote( players[ i ].get_id(), true );
	}

	// Main loop
	while( !s3eDeviceCheckQuitRequest() )
	{
		// Clear the drawing surface
		Iw2DSurfaceClear( 0xff0000ff );

		// Show the drawing surface
		Iw2DSurfaceShow();

		// Yield to the OS
		s3eDeviceYield( 0 );
	}
	
	// Clean up
	Iw2DTerminate();

	return 0;
}
