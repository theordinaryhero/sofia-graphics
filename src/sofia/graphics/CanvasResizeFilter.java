package sofia.graphics;

/**
 * This class returns either the original pixels or transparent pixels
 * depending if the given pixel exists in the original picture.
 * @author Tk
 *
 */
public class CanvasResizeFilter extends ImageFilter
{
	ImageLike image2, image1;
	
	/**
	 * Creates a new CanvasResizeFilter returns the original pixel if it exists
	 * in the image or a transparent pixel if it doesn't.
	 * @param image1 the picture the original color is coming from
	 * @param image2 the picture you're comparing the width and height to
	 */
	
	public CanvasResizeFilter(ImageLike image1, ImageLike image2) {
		super(image1);
		this.image2 = image2;
	}

	@Override
	public Color getPixel(int x, int y) {
		int greaterHeight = 0;
		int greaterWidth = 0;
		Color newColor = null;
		
		// Returns the greater of the height between the two images
		if ( image1.getHeight() < image2.getHeight())
		{
			greaterHeight = image2.getHeight();
		}
		else
		{
			greaterHeight = image1.getHeight();
		}
		
		// Returns the greater of the width between the two images
		if ( image1.getWidth() < image2.getWidth())
		{
			greaterWidth = image2.getWidth();
		}
		else
		{
			greaterWidth = image1.getWidth();
		}
		
		// Returns the color of image1 if it's pixels are less than the greatest
		// width and height
		if ( x < image1.getWidth() && y < image1.getHeight())
		{
			newColor = image1.getPixel(x, y);
		}
		else if (x < greaterWidth && y < greaterHeight)
		{
			newColor = Color.rgb(0, 0, 0, 0);
		}
		
		return newColor;
		
	}
	
	
}
