package sofia.graphics;

/**
 * This class creates a new SharpenImage filter which sharpens an image.
 * It does so by getting the RGB values from a pixel and multiplying it by 8. 
 * Each surrounding pixels' RGB values are then multiplied by negative one.
 * Then each RGB component of every pixel is added together. The summation is
 * multiplied by scaleFactor and added to the original RGB value of the current
 * pixel. If a component is less than 0 it is capped at 0 and if a component is
 * more than 255 it is capped at 255. 
 * 
 * @author Tk
 *
 */
public class SharpenImage extends ImageFilter
{
	
	double scaleFactor;
	
	public SharpenImage(ImageLike image, double scaleFactor)
	{
		super(image);
		this.scaleFactor = scaleFactor;
	}

	@Override
	public Color getPixel(int x, int y) {
		// TODO Auto-generated method stub
		
		Color newColor = null;
		
		if ( x == 0 || x == getImage().getWidth() - 1 || y == 0 
				|| y == getImage().getHeight() - 1)
		{
			newColor = getImage().getPixel(x, y);
		}
		else
		{
			Color originalColor = getImage().getPixel(x, y);
			int currentPxlRed = originalColor.red() * 8;
			int currentPxlGreen = originalColor.green() * 8;
			int currentPxlBlue = originalColor.blue() * 8;
			
			// Diagonal Top left/right and Diagonal Bottom left/right			
			int diagTLRed = -1 * getImage().getPixel(x - 1, y - 1).red();
			int diagTLGreen = -1 * getImage().getPixel(x - 1, y - 1).green();
			int diagTLBlue = -1 * getImage().getPixel(x - 1, y - 1).blue();
			
			int diagTRRed = -1 * getImage().getPixel(x + 1, y - 1).red();
			int diagTRGreen = -1 * getImage().getPixel(x + 1, y - 1).green();
			int diagTRBlue = -1 * getImage().getPixel(x + 1, y - 1).blue();
			
			int diagBLRed = -1 * getImage().getPixel(x - 1, y + 1).red();
			int diagBLGreen = -1 * getImage().getPixel(x - 1, y + 1).green();
			int diagBLBlue = -1 * getImage().getPixel(x - 1, y + 1).blue();
			
			int diagBRRed = -1 * getImage().getPixel(x + 1, y + 1).red();
			int diagBRGreen = -1 * getImage().getPixel(x + 1, y + 1).green();
			int diagBRBlue = -1 * getImage().getPixel(x + 1, y + 1).blue();
			
			// Left, top, right, and bottom
			int leftRed = -1 * getImage().getPixel(x - 1, y).red();
			int leftGreen = -1 * getImage().getPixel(x - 1, y).green();
			int leftBlue = -1 * getImage().getPixel(x - 1, y).blue();
			
			int rightRed = -1 * getImage().getPixel(x + 1, y).red();
			int rightGreen = -1 * getImage().getPixel(x + 1, y).green();
			int rightBlue = -1 * getImage().getPixel(x + 1, y).blue();
			
			int topRed = -1 * getImage().getPixel(x, y - 1).red();
			int topGreen = -1 * getImage().getPixel(x, y - 1).green();
			int topBlue = -1 * getImage().getPixel(x, y - 1).blue();
			
			int bottomRed = -1 * getImage().getPixel(x, y + 1).red();
			int bottomGreen = -1 * getImage().getPixel(x, y + 1).green();
			int bottomBlue = -1 * getImage().getPixel(x, y + 1).blue();
			
			// Summation of a pixel's RGB components and its surrounding
			// RGB components
			int maskRed = (diagTLRed + diagTRRed + diagBLRed + diagBRRed +
					leftRed + rightRed + bottomRed + topRed + currentPxlRed) ;
			int maskGreen = (diagTLGreen + diagTRGreen + diagBLGreen +
					diagBRGreen + leftGreen + rightGreen + bottomGreen 
					+ topGreen + currentPxlGreen) ;
			int maskBlue = (diagTLBlue + diagTRBlue + diagBLBlue + diagBRBlue +
					leftBlue + rightBlue + bottomBlue + topBlue +
					currentPxlBlue) ;
			

			// The original RGB components are added with the product of the 
			// input scale factor and the mask color
			int newRed = (int) (originalColor.red() + scaleFactor * maskRed);
			int newGreen = (int) (originalColor.green() + scaleFactor * maskGreen);
			int newBlue = (int) (originalColor.blue() + scaleFactor * maskBlue);
			
			// Caps each component at 255 if it is greater and at 0 if it lesser
			if ( newRed > 255)
			{
				newRed = 255;
			}
			else if ( newRed < 0)
			{
				newRed = 0;
			}
			if ( newGreen > 255)
			{
				newGreen = 255;
			}
			else if ( newGreen < 0)
			{
				newGreen = 0;
			}
			if ( newBlue > 255)
			{
				newBlue = 255;
			}
			else if ( newBlue < 0)
			{
				newBlue = 0;
			}
			
			// Creates a new color from the new RGB values
			newColor = Color.rgb(newRed, newGreen, newBlue);
			
		}
		
		return newColor;
		
	}

}
