package sofia.graphics;

/**
 * This class creates a blur image filter that will blur any given picture.
 * It does so by averaging each color component (RGB) and setting the pixel
 * color to the new color.
 * 
 * @author Tk
 *
 */

public class BlurImage extends ImageFilter{
	
	/**
	 * Creates a new BlurImage object
	 * 
	 * @param image The image to be blurred
	 */
	public BlurImage(ImageLike image)
	{
		super(image);
	}

	@Override
	public Color getPixel(int x, int y) {
		
		Color newColor = null;
		
		// Check to make sure the first and last row and the first and last
		// column aren't part of the process
		if ( x == 0 || x == getImage().getWidth() - 1 || y == 0 
				|| y == getImage().getHeight() - 1)
		{
			newColor = getImage().getPixel(x, y);
		}
		
		else
		{
			// Diagonal Top left/right and Diagonal Bottom left/right color
			Color diagTL = getImage().getPixel(x - 1, y - 1);
			Color diagTR = getImage().getPixel(x + 1, y - 1);
			Color diagBL = getImage().getPixel(x - 1, y + 1);
			Color diagBR = getImage().getPixel(x + 1, y + 1);
					
			// Left, top, right, and bottom color
			Color left = getImage().getPixel(x - 1, y);
			Color right = getImage().getPixel(x + 1, y);
			Color top = getImage().getPixel(x, y - 1);
			Color bottom = getImage().getPixel(x, y + 1);
					
			// Average the red, green, and blue from the
			// surrounding pixels.
					
			int newRed = ( diagTL.red() + diagTR.red() + diagBL.red()
					+ diagBR.red() + left.red() + right.red() 
					+ top.red() + bottom.red() ) / 8;
			int newGreen = ( diagTL.green() + diagTR.green()
					+ diagBL.green() + diagBR.green() + left.green()
					+ right.green() + top.green() + bottom.green() )/ 8;
			int newBlue = ( diagTL.blue() + diagTR.blue() 
					+ diagBL.blue() + diagBR.blue() + left.blue()
					+ right.blue() + top.blue() + bottom.blue() ) / 8;
					
			// the new color is made from the above averages.
			
			newColor = Color.rgb(newRed, newGreen, newBlue);
		}
		return newColor;
	}
	
	

}
