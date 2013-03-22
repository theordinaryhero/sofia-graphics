package sofia.graphics;

/**
 * This class creates a NegateImage filter which will negate an image.
 * It does so by getting the color of a pixel, subtracting the original RGB
 * value from 255, and then sets the color of the pixel to those new colors. 
 * @author Tk
 *
 */
public class NegateImage extends ImageFilter
{	
	/**
	 * Creates a new NegateImage object
	 * @param image The image to be negated
	 */
	public NegateImage(ImageLike image)
	{
		super(image);
	}

	@Override
	public Color getPixel(int x, int y) {

		// Color for the given pixel is retrieved and each component is 
		// subtracted from 255.
		Color theColor = getImage().getPixel(x, y);
        int redValue = theColor.red();
        int blueValue = theColor.blue();
        int greenValue = theColor.green();
        Color newColor = Color.rgb(255 - redValue, 255 - blueValue,
            255 - greenValue);
        // returns the value of the new Color
        return newColor;
	}

}
