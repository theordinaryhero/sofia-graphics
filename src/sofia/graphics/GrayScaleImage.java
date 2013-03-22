package sofia.graphics;

/**
 * This class creates a new GrayScaleImage filter.
 * It does so by averaging each color component together and setting each
 * component by this new value.
 * 
 * @author Tk
 *
 */
public class GrayScaleImage extends ImageFilter 
{

	/**
	 * Creates a new GrayScaleImage
	 * @param image The image to be black and white
	 */
	public GrayScaleImage(ImageLike image) {
		super(image);
		// TODO Auto-generated constructor stub
	}


	@Override
	public Color getPixel(int x, int y) {
		
		// Gets the color of each component
        Color theColor = getImage().getPixel(x, y);
        int redValue = theColor.red();
        int blueValue = theColor.blue();
        int greenValue = theColor.green();
        
        // Averages each component together and sets each component by this
        // value
        int intensity = (redValue + blueValue + greenValue) / 3;
        Color newColor = Color.rgb(intensity, intensity, intensity);
        return newColor;

	}
	
}
