package sofia.media.comp;

import java.io.File;

import sofia.graphics.Color;
import sofia.graphics.Image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

/**
 * A class that represents a simple picture.  A simple picture may have
 * an associated file name and a title.  A simple picture has pixels, 
 * width, and height.  A simple picture uses an Image to 
 * hold the pixels.  You can show a simple picture in a 
 * PictureFrame (a JFrame).
 * 
 * Copyright Georgia Institute of Technology 2004
 * @author Barb Ericson ericson@cc.gatech.edu
 */
public class SimplePicture implements DigitalPicture
{
  
  /////////////////////// Fields /////////////////////////
  
  /**
   * the file name associated with the simple picture
   */
  private String fileName;
  
  /**
   * the title of the simple picture
   */
  private String title;
  
  /**
   * Image to hold pixels for the simple picture
   */
  private Image picture;
  
 
 /////////////////////// Constructors /////////////////////////
 
 /**
  * A Constructor that takes no arguments.  All fields will be null.
  * The default width and height is 200. The picture will be a black white
  * canvas.
  * 
  * A no-argument constructor must be given in order for a class to
  * be able to be sub-classed.  By default all subclasses will implicitly
  * call this in their parent's no argument constructor unless a 
  * different call to super() is explicitly made as the first line 
  * of code in a constructor.
  */
 public SimplePicture() 
 {
	 Bitmap image = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
	 this.picture = new Image(image);
	 setAllPixelsToAColor(Color.white);
	   this.title = "Untitled";
	   this.fileName = "Untitled";
 }
 
 /**
  * A Constructor that takes a file name and uses it to create a simple picture
  * @param fileName The file name to use in creating the picture
  */
 public SimplePicture(String fileName)
 {
   this.fileName = fileName;  
   load(fileName);
   this.title = "Untitled";
 }
 
 /**
  * A Constructor that takes an Uri and uses it to create a simple picture
  */
 
 public SimplePicture(Uri uriOfImage)
 {
	 fileName = uriOfImage.getPath();
	 load(fileName);
	   this.title = "Untitled";
 }
 
 /**
  * A constructor that takes the width and height desired for a picture and
  * creates an image of that size. The picture will have a blank white canvas.
  * @param width the desired width
  * @param height the desired height
  */
 public  SimplePicture(int width, int height)
 {
   Bitmap pic = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
   this.picture = new Image(pic);
   this.title = "Untitled";
   this.fileName = "Untitled";
   setAllPixelsToAColor(Color.white);
 }
 
 /**
  * A constructor that takes the width and height desired for a picture and
  * creates an image of that size.  It also takes the color to use for the
  * background of the picture.
  * @param width the desired width
  * @param height the desired height
  * @param theColor the background color for the picture
  */
 public  SimplePicture(int width, int height, Color theColor)
 {
   this(width,height);
   setAllPixelsToAColor(theColor);
   this.title = "Untitled";
   this.fileName = "Untitled";
 }
 
 /**
  * A Constructor that takes a picture to copy information from
  * @param copyPicture the picture to copy from
  */
 public SimplePicture(SimplePicture copyPicture)
 {
   if (copyPicture.fileName != null)
   {
      this.fileName = new String(copyPicture.fileName);
   }
   if (copyPicture.title != null)
      this.title = new String(copyPicture.title);
   if (copyPicture.picture != null)
   {
     this.copyPicture(copyPicture);
   }
 }
 
 /**
  * A constructor that takes a Bitmap image and creates a simple picture
  * from it.
  * @param image the buffered image
  */
 public SimplePicture(Bitmap image)
 {
   this.picture = new Image(image);
   this.title = "Untitled";
   this.fileName = "Untitled";
 }
 
 
 ////////////////////////// Methods //////////////////////////////////

 /**
  * Method that will copy all of the passed source picture into
  * the current picture object 
  * @param sourcePicture  the picture object to copy
  */
 public void copyPicture(SimplePicture sourcePicture)
 {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   
   // loop through the columns
   for (int sourceX = 0, targetX = 0; 
        sourceX < sourcePicture.getWidth() &&
        targetX < this.getWidth();
        sourceX++, targetX++)
   {
	   // loop through the rows
	   for (int sourceY = 0, targetY = 0; 
		   sourceY < sourcePicture.getHeight() && 
           targetY < this.getHeight();
		   sourceY++, targetY++)
	   {
		   // Retrieve the color of the source and set the target to that color
		   sourcePixel = sourcePicture.getPixel(sourceX,sourceY);
		   targetPixel = this.getPixel(targetX,targetY);
		   targetPixel.setColor(sourcePixel.getColor());
	   }
   }
   
 }
 
 /**
  * Method to set the picture to the specified color
  * @param color the color to set to
  */
 public void setAllPixelsToAColor(Color color)
 {
   // loop through all x
   for (int x = 0; x < this.getWidth(); x++)
   {
     // loop through all y
     for (int y = 0; y < this.getHeight(); y++)
     {
       getPixel(x,y).setColor(color);
     }
   }
 }
 
 /**
  * Method to get a graphics object for this picture to use to draw on
  * @return a graphics object to use for drawing
  */
 //public Graphics getGraphics()
 {
	 // TODO
 }
 
 /**
  * Method to get a Graphics2D object for this picture which can
  * be used to do 2D drawing on the picture
  */
// public Graphics2D createGraphics()
 {
	// TODO
 }
 
 /**
  * Method to get the file name associated with the picture
  * @return  the file name associated with the picture
  */
 public String getFileName() 
 { 
	 return fileName;
 }
 
 /**
  * Method to set the file name
  * @param name the full pathname of the file
  */
 public void setFileName(String name)
 {
   this.fileName = name;
 }
 
 /**
  * Method to get the title of the picture
  * @return the title of the picture
  */
 public String getTitle() 
 { return title; }
 
 /**
  * Method to set the title for the picture
  * @param title the title to use for the picture
  */
 public void setTitle(String title) 
 {
   this.title = title;
 }
 
 /**
  * Method to get the width of the picture in pixels
  * @return the width of the picture in pixels
  */
 public int getWidth() { return picture.getWidth(); }
 
 /**
  * Method to get the height of the picture in pixels
  * @return  the height of the picture in pixels
  */
 public int getHeight() { return picture.getHeight(); }
 
 
 /**
  * Method to get an image from the picture
  * @return  the Image representation of simple picture
  */
 public Image getImage()
 {
   return picture;
 }
 
 /**
  * Method to return the pixel value as an int for the given x and y location
  * @param x the x coordinate of the pixel
  * @param y the y coordinate of the pixel
  * @return the pixel value as an integer (alpha, red, green, blue)
  */
 public int getBasicPixel(int x, int y)
 {
    return picture.getPixel(x, y).toRawColor();
 }
    
 /** 
  * Method to set the value of a pixel in the picture from an int
  * @param x the x coordinate of the pixel
  * @param y the y coordinate of the pixel
  * @param rgb the new rgb value of the pixel (alpha, red, green, blue)
  */     
 public void setBasicPixel(int x, int y, int rgb)
 {
   picture.setPixel(x, y, Color.fromRawColor(rgb));
 }
  
 /**
  * Method to get a pixel object for the given x and y location
  * @param x  the x location of the pixel in the picture
  * @param y  the y location of the pixel in the picture
  * @return a Pixel object for this location
  */
 public Pixel getPixel(int x, int y)
 {
   // create the pixel object for this picture and the given x and y location
   Pixel pixel = new Pixel(this,x,y);
   return pixel;
 }
 
 /**
  * Method to get a two-dimensional array of Pixels for this simple picture
  * @return a two-dimensional array of Pixel objects starting with y=0
  * to y=height-1 and x=0 to x=width-1.
  */
 public Pixel[][] getPixels()
 {
	 // 
	 int width = getWidth();
	 int height = getHeight();
	 Pixel[][] pixelArray = new Pixel[height][width];
   
	 // loop through height rows from top to bottom
	 for (int row = 0; row < height; row++)
	 {
		 for (int col = 0; col < width; col++)
		 {
			 pixelArray[row][height] = new Pixel(this,col,row);
		 }
	 }
	   
	 return pixelArray;
 }
 
 /**
  * Method to hide the picture
  */
 //public void hide()
 {
	// TODO
 }
 
 /**
  * Method to make this picture visible or not
  * @param flag true if you want it visible else false
  */
 //public void setVisible(boolean flag)
 {
	// TODO
 }

 /**
  * Method to open a picture explorer on a copy of this simple picture
  */
// public void explore()
 {
	// TODO
 }
 
 /**
  * Method to force the picture to redraw itself.  This is very
  * useful after you have changed the pixels in a picture.
  */
 //public void repaint()
 {
	// TODO
 }
 
 /**
  * Method to load the picture from the passed file name
  * @param fileName the file name to use to load the picture from
  */
// public void loadOrFail(String fileName) throws IOException
 {
	// TODO
 }



 /**
  * Method to load the picture from the passed file name
  * this just calls load(fileName) and is for name compatibility
  * @param fileName the file name to use to load the picture from
  * @return true if success else false
  */
// public boolean loadImage(String fileName)
 {
	// TODO
}
 
 /**
  * Method to draw a message as a string on the buffered image 
  * @param message the message to draw on the buffered image
  * @param xPos  the leftmost point of the string in x 
  * @param yPos  the bottom of the string in y
  */
 //public void addMessage(String message, int xPos, int yPos)
 {
	// TODO
 }
 
 /**
  * Method to draw a string at the given location on the picture
  * @param text the text to draw
  * @param xPos the left x for the text 
  * @param yPos the top y for the text
  */
 //public void drawString(String text, int xPos, int yPos)
 {
	// TODO
 }
 
 /**
   * Method to create a new picture by scaling the current
   * picture by the given x and y factors
   * @param xFactor the amount to scale in x
   * @param yFactor the amount to scale in y
   * @return the resulting picture
   */
public SimplePicture scale(double xFactor, double yFactor)
  {
	int scaleWidth = (int) (xFactor * getWidth());
	int scaleHeight = (int) (yFactor * getHeight());
	
	Bitmap image = Bitmap.createScaledBitmap(picture.asBitmap(), scaleWidth, scaleHeight, true);
	return new SimplePicture(image);
	
  }
  
  /**
   * Method to create a new picture of the passed width. 
   * The aspect ratio of the width and height will stay
   * the same.
   * @param width the desired width
   * @return the resulting picture
   */
public SimplePicture getPictureWithWidth(int width)
  {

    double xFactor = (double) width / this.getWidth();
    SimplePicture result = scale(xFactor,xFactor);
    return result;
  }
  
  /**
   * Method to create a new picture of the passed height. 
   * The aspect ratio of the width and height will stay
   * the same.
   * @param height the desired height
   * @return the resulting picture
   */
public SimplePicture getPictureWithHeight(int height)
  {
    double yFactor = (double) height / this.getHeight();
    SimplePicture result = scale(yFactor,yFactor);
    return result;
  }
 
 /**
  * Method to load a picture from a file name and show it in a picture frame
  * @param fileName the file name to load the picture from
  * @return true if success else false
  */
// public boolean loadPictureAndShowIt(String fileName)
 {
	// TODO
 }
 
 /**
  * Method to write the contents of the picture to a file with 
  * the passed name
  * @param fileName the name of the file to write the picture to
  */
 //public void writeOrFail(String fileName) throws IOException
 {
	// TODO
 }

 /**
  * Method to write the contents of the picture to a file with 
  * the passed name without throwing errors
  * @param fileName the name of the file to write the picture to
  * @return true if success else false
  */
// public boolean write(String fileName)
 {
	// TODO
 }

 /**
  * Method to set the media path by setting the directory to use
  * @param directory the directory to use for the media path
  */
 //public static void setMediaPath(String directory) { 
 //}
 
 /**
  * Method to get the directory for the media
  * @param fileName the base file name to use
  * @return the full path name by appending
  * the file name to the media directory
  */
//public static String getMediaPath(String fileName) 
{
	// TODO
		
}
 
  /**
   * Method to get the coordinates of the enclosing rectangle after this
   * transformation is applied to the current picture
   * @return the enclosing rectangle
   */
//public Rectangle2D getTransformEnclosingRect(AffineTransform trans)
  {
    // TODO
	  
  }
  
  /**
   * Method to get the coordinates of the enclosing rectangle after this
   * transformation is applied to the current picture
   * @return the enclosing rectangle
   */
 // public Rectangle2D getTranslationEnclosingRect(AffineTransform trans)
  {
	  // TODO
  }
 
 /**
  * Method to return a string with information about this picture
  * @return a string with information about the picture 
  */
 public String toString()
 {
   String output = "Simple Picture, filename: " + fileName + 
     " ,height: " + getHeight() + " , and width: " + getWidth() +"";
   return output;
 }

@Override
public Bitmap getBitmap() {
	// TODO Auto-generated method stub
	return picture.asBitmap();
}


@Override
public boolean load(String fileName) {
	// TODO Auto-generated method stub
	
	Bitmap image = BitmapFactory.decodeFile(fileName);
	this.picture = new Image(image);
	if (this.picture == null)
	{
		return false;
	}
	else
	{
		return true;
	}
}

@Override
public void show() {
	// TODO Auto-generated method stub
	
}

@Override
public void load(Image image) {
	// TODO Auto-generated method stub
	this.picture = image;
}

} // end of SimplePicture class
