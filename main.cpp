# include "Iw2D.h"

int main()
{
	// Initialise the 2D graphics system
	Iw2DInit();

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
