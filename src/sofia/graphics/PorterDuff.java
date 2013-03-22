package sofia.graphics;

/**
 * This class contains all the PorterDuff operation available including:
 * Normal, Screen, Multiply, Overlay, Hard Light, Soft light, Difference,
 * Color Burn, Color Dodge, Darken, Lighten, and Exclusion.
 * @author Tk
 *
 */
public abstract class PorterDuff
{
	public abstract Color apply(Color source, Color destination);

	/*
	 * Normal just layers the source over the destination, no blending involved.
	 */
	public static final PorterDuff NORMAL = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			// Returns the source color
			return source;
		}
	};
	
	/*
	 * Multiply takes the product of each RGB value of the source and 
	 * destination, creates a new color from the products, and then returns it.
	 */
	public static final PorterDuff MULTIPLY = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			// Multiplies each RGB component, creates a new color from those
			// new values, and returns it
			
			float sourceRed = (source.red()/255);
			float destinationRed = (destination.red()/255);
			int newRed = (int) (( sourceRed * destinationRed ) * 255);
			
			float sourceGreen = (source.green()/255);
			float destinationGreen = (destination.green()/255);
			int newGreen = (int) ((sourceGreen * destinationGreen) * 255);
			
			float sourceBlue = (source.blue()/255);
			float destinationBlue = (destination.blue()/255);
			int newBlue = (int) ((sourceBlue * destinationBlue) * 255);
			
			return Color.rgb(newRed, newGreen, newBlue);
			
			
		}
	};
	
	/*
	 * Screen adds the RGB values of the source and destination. Then it
	 * subtracts the product of the RGB values of the source and destination.
	 * These computations are taking the complement of each RGB value, 
	 * multiplying the complements, and then complementing the result.
	 */
	public static final PorterDuff SCREEN = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			// Just converts the int representations of the colors to a number
			// less than one
			float sourceRed = (source.red()/255);
			float sourceGreen = (source.green()/255);
			float sourceBlue = (source.blue()/255);
			
			float destRed = (destination.red()/255);
			float destGreen = (destination.green()/255);
			float destBlue = (destination.blue()/255);
			
			// Add each RGB value and subtract the product of the source and 
			// destination
			int newRed = (int) (((sourceRed + destRed - (sourceRed * destRed)))/255);
			int newGreen = (int) (((sourceGreen + destGreen - (sourceGreen * destGreen)))/255);
			int newBlue = (int) (((sourceBlue + destBlue - (sourceBlue * destBlue)))/255);
			
			// Creates a new color from the new values above
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};	
	
	public static final PorterDuff OVERLAY = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			int newRed = 0;
			int newGreen = 0;
			int newBlue = 0;
			
			float sourceRed = ((source.red()/255) * 2);
			float destRed = (destination.red()/255);
			if (destRed <= 0.5)
			{
				newRed = (int) ((sourceRed * destRed ) * 255);
			}
			else
			{
				newRed = (int) (((destRed + sourceRed - 1 - (sourceRed * destRed) + destRed)) / 255);
			}
			
			float sourceGreen = ((source.green()/255) * 2);
			float destGreen = (destination.green()/255);
			if (destGreen <= 0.5)
			{
				newGreen = (int) ((sourceGreen * destGreen ) * 255);
			}
			else
			{
				newGreen = (int) (((destGreen + sourceGreen - 1 - (sourceGreen * destGreen) + destGreen)) / 255);
			}
			
			float sourceBlue = ((source.blue()/255) * 2);
			float destBlue = (destination.blue()/255);
			if (destBlue <= 0.5)
			{
				newBlue = (int) ((sourceBlue * destBlue ) * 255);
			}
			else
			{
				newBlue = (int) (((destBlue + sourceBlue - 1 - (sourceBlue * destBlue) + destBlue)) / 255);
			}
			
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	/*
	 * Darken compares the source and destination Colors and whichever is 
	 * darker, then that color is returned.
	 */
	public static final PorterDuff DARKEN = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			int newRed = 0;
			int newGreen = 0;
			int newBlue = 0;
			
			// Sets each RGB value depending on which one is lesser
			if (source.red() < destination.red())
			{
				newRed = source.red();
			}
			else
			{
				newRed = destination.red();
			}
			
			if (source.green() < destination.green())
			{
				newGreen = source.green();
			}
			else
			{
				newGreen = destination.green();
			}
			if (source.blue() < destination.blue())
			{
				newBlue = source.blue();
			}
			else
			{
				newBlue = destination.blue();
			}
			
			// Creates a Color from these new values
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	/*
	 * Lighten compares the source and destination Colors and whichever is 
	 * lighter, then that color is returned.
	 */
	public static final PorterDuff LIGHTEN = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			int newRed = 0;
			int newGreen = 0;
			int newBlue = 0;
			
			// Sets each RGB value depending on which one is greater
			if (source.red() > destination.red())
			{
				newRed = source.red();
			}
			else
			{
				newRed = destination.red();
			}
			
			if (source.green() > destination.green())
			{
				newGreen = source.green();
			}
			else
			{
				newGreen = destination.green();
			}
			if (source.blue() > destination.blue())
			{
				newBlue = source.blue();
			}
			else
			{
				newBlue = destination.blue();
			}
			
			// Creates a color from the new RGB values  
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	public static final PorterDuff COLORBURN = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			/*
			 * if(Cb == 1)
        B(Cb, Cs) = 1
    else
    if(Cs == 0)
        B(Cb, Cs) = 0
    else
        B(Cb, Cs) = 1 - min(1, (1 - Cb) / Cs)
			 */
			
			int newRed = 0;
			int newGreen = 0;
			int newBlue = 0;
			
			float sourceRed = source.red() / 255;
			float sourceGreen = source.green() / 255;
			float sourceBlue = source.blue() / 255;
			
			float destRed = destination.red() / 255;
			float destGreen = destination.green() / 255;
			float destBlue = destination.blue() / 255;
			
			if ( destRed == 1)
			{
				newRed = 1;
			}
			else
			{
				if (sourceRed == 0)
				{
					newRed = 0;
				}
				else
				{
					newRed = (int) (1 - Math.min(1, (1 - destRed)/sourceRed));
				}
			}
			if ( destGreen == 1)
			{
				newGreen = 1;
			}
			else
			{
				if (sourceGreen == 0)
				{
					newGreen = 0;
				}
				else
				{
					newGreen = (int) (1 - Math.min(1, (1 - destGreen)/sourceGreen));
				}
			}
			if ( destBlue == 1)
			{
				newBlue = 1;
			}
			else
			{
				if (sourceBlue == 0)
				{
					newBlue = 0;
				}
				else
				{
					newBlue = (int) (1 - Math.min(1, (1 - destBlue)/sourceBlue));
				}
			}
			
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	public static final PorterDuff HARDLIGHT = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			int newRed = 0;
			int newGreen = 0;
			int newBlue = 0;
			
			float sourceRed = ((source.red()/255) * 2);
			float destRed = (destination.red()/255);
			if (sourceRed <= 0.5)
			{
				newRed = (int) ((sourceRed * destRed ) * 255);
			}
			else
			{
				newRed = (int) (((destRed + sourceRed - 1 - (sourceRed * destRed) + destRed)) / 255);
			}
			
			float sourceGreen = ((source.green()/255) * 2);
			float destGreen = (destination.green()/255);
			if (sourceGreen <= 0.5)
			{
				newGreen = (int) ((sourceGreen * destGreen ) * 255);
			}
			else
			{
				newGreen = (int) (((destGreen + sourceGreen - 1 - (sourceGreen * destGreen) + destGreen)) / 255);
			}
			
			float sourceBlue = ((source.blue()/255) * 2);
			float destBlue = (destination.blue()/255);
			if (sourceBlue <= 0.5)
			{
				newBlue = (int) ((sourceBlue * destBlue ) * 255);
			}
			else
			{
				newBlue = (int) (((destBlue + sourceBlue - 1 - (sourceBlue * destBlue) + destBlue)) / 255);
			}
			
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	public static final PorterDuff SOFTLIGHT = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			int newRed = 0;
			int newGreen = 0;
			int newBlue = 0;
			
			float sourceRed = source.red() / 255;
			float sourceGreen = source.green() / 255;
			float sourceBlue = source.blue() / 255;
			
			float destRed = destination.red() / 255;
			float destGreen = destination.green() / 255;
			float destBlue = destination.blue() / 255;
			
			double destRed2 = 0;
			double destGreen2 = 0;
			double destBlue2 = 0;
			
			if ( sourceRed <= 0.5)
			{
				newRed = (int) (destRed - ( 1 - (2 * sourceRed)) * destRed * (1 - destRed));
			}
			else
			{
				if (destRed <= 0.25)
				{
					destRed2 = ((16 * destRed - 12) * destRed + 4) * destRed;
				}
				else
				{
					destRed2 = (float) Math.sqrt(destRed);
				}
				newRed = (int) (destRed + (2 * sourceRed - 1) * (destRed2 - destRed));
			}
			if ( sourceGreen <= 0.5)
			{
				newGreen = (int) (destGreen - ( 1 - (2 * sourceGreen)) * destGreen * (1 - destGreen));
			}
			else
			{
				if (destGreen <= 0.25)
				{
					destGreen2 = ((16 * destGreen - 12) * destGreen + 4) * destGreen;
				}
				else
				{
					destGreen2 = (float) Math.sqrt(destGreen);
				}
				newGreen = (int) (destGreen + (2 * sourceGreen - 1) * (destGreen2 - destGreen));
			}
			if ( sourceBlue <= 0.5)
			{
				newBlue = (int) (destBlue - ( 1 - (2 * sourceBlue)) * destBlue * (1 - destBlue));
			}
			else
			{
				if (destBlue <= 0.25)
				{
					destBlue2 = ((16 * destBlue - 12) * destBlue + 4) * destBlue;
				}
				else
				{
					destBlue2 = (float) Math.sqrt(destBlue);
				}
				newBlue = (int) (destBlue + (2 * sourceBlue - 1) * (destBlue2 - destBlue));
			}
			
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	/*
	 * Difference takes the absolute value of the subtraction of the destination
	 * color and the source color.
	 */
	public static final PorterDuff DIFFERENCE = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			// The absolute value of the difference from the source from the
			// destination for each RGB component is taken
			int newRed = Math.abs(destination.red() - source.red());
			int newGreen = Math.abs(destination.green() - source.green());
			int newBlue = Math.abs(destination.blue() - source.blue());
			
			// Creates a new color from the new RGB values
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
	public static final PorterDuff EXCLUSION = new PorterDuff()
	{
		@Override
		public Color apply(Color source, Color destination)
		{ 
			float sourceRed = source.red() / 255;
			float sourceGreen = source.green() / 255;
			float sourceBlue = source.blue() / 255;
			
			float destRed = destination.red() / 255;
			float destGreen = destination.green() / 255;
			float destBlue = destination.blue() / 255;
					
			int newRed = (int) ((sourceRed+destRed - (2*(sourceRed * destRed))) * 255);
			int newGreen = (int) ((sourceGreen + destGreen - 
					(2*(sourceGreen*destGreen))) * 255);
			int newBlue = (int) ((sourceBlue + destBlue -
					(2 * (sourceBlue * destBlue))) * 255);
			
			return Color.rgb(newRed, newGreen, newBlue);
		}
	};
	
}
