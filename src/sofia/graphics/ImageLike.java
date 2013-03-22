package sofia.graphics;

import android.content.Context;
import android.graphics.Bitmap;

public interface ImageLike {

	
	public Bitmap asBitmap();
	
	public Color getPixel(int x, int y);
	
	public void resolveAgainstContext(Context context);
	
	public int getWidth();
	
	public int getHeight();

}
