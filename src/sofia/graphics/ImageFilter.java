package sofia.graphics;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class ImageFilter implements ImageLike
{
	private ImageLike image;
	private Bitmap newImage;
	
	public ImageFilter(ImageLike image)
	{
		this.image = image;
	}
	
	@Override
	public Bitmap asBitmap() {
		// TODO Auto-generated method stub
		
		newImage = Bitmap.createBitmap(getWidth(), getHeight(), 
				Bitmap.Config.ARGB_8888);
		 

        for (int x = 0; x < getWidth(); x++)
        {
            for (int y = 0; y < getHeight(); y++)
            {

                Color theColor = getPixel(x, y);
                newImage.setPixel(x, y, theColor.toRawColor());
            }
        }
        return newImage;
	}

	@Override
	public abstract Color getPixel(int x, int y);
	
	public int getWidth()
	{
		return image.getWidth();
	}

	public int getHeight()
	{
		return image.getHeight();
	}
	
	public ImageLike getImage()
	{
		return image;
	}
	
	@Override
	public void resolveAgainstContext(Context context) {
		// TODO Auto-generated method stub
		image.resolveAgainstContext(context);
	}
	
	
	
}
