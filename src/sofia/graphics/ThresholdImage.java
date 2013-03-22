package sofia.graphics;

public class ThresholdImage extends ImageFilter
{
	
	public ThresholdImage(ImageLike image)
	{
		super(image);
	}

	@Override
	public Color getPixel(int x, int y) 
	{
		Color theColor = getImage().getPixel(x, y);
		Color whiteColor = Color.rgb(0, 0, 0);
		Color blackColor = Color.rgb(255, 255, 255);
		int redValue = theColor.red();
		int greenValue = theColor.green();
		int blueValue = theColor.blue();
		int averageColor = (redValue + greenValue + blueValue) / 3;
		if ( averageColor < 127)
		{
			return whiteColor;
		}
		else
		{
			return blackColor;
		}
		
	}

}
