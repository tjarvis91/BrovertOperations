# include "Iw2D.h"

int main()
{
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
	Player players[ 5 ];
	for( int i=0; i<5; i++ )
	{
		players[ i ] = game.get_player( i );
	}

	// Make selection
	int crown_player_id = game.get_crown();
	game.make_selection( crown_player_id, new vector( 1, 3, 4 ) );

	// Make votes
	for( int i=0; i<5; i++ )
	{
		game.add_selection_vote( players[ i ].id, true );
	}

	// Main loop
	while( !s3eDeviceCheckQuitRequest() )
	{
		// Clear the drawing surface
		Iw2DSurfaceClear( 0xff000000 );

		// Show the drawing surface
		Iw2DSurfaceShow();

		// Yield to the OS
		s3eDeviceYield(0);
	}
	
	// Clean up
	Iw2DTerminate();

	return 0;
}
