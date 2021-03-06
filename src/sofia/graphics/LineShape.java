package sofia.graphics;

import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

//-------------------------------------------------------------------------
/**
 * A shape that is drawn as a line between two points.
 * 
 * @author  Tony Allevato
 * @version 2011.09.29
 */
public class LineShape extends StrokedShape
{
	//~ Constructors ..........................................................

    // ----------------------------------------------------------
	/**
	 * Creates a new {@code LineShape} with default position and size.
	 */
    public LineShape()
    {
        super();
    }


    // ----------------------------------------------------------
	/**
	 * Creates a new {@code LineShape} between two points.
	 * 
	 * @param bounds the bounding rectangle of the line
	 */
    public LineShape(PointF point1, PointF point2)
    {
        setBounds(new RectF(point1.x, point1.y, point2.x, point2.y));
    }


    // ----------------------------------------------------------
	/**
	 * Creates a new {@code LineShape} between two points.
	 * 
	 * @param x1 the x-coordinate of the first endpoint
	 * @param y1 the y-coordinate of the first endpoint
	 * @param x2 the x-coordinate of the second endpoint
	 * @param y2 the y-coordinate of the second endpoint
	 */
    public LineShape(float x1, float y1, float x2, float y2)
    {
        setBounds(new RectF(x1, y1, x2, y2));
    }


    // ----------------------------------------------------------
	/**
	 * Creates a new {@code LineShape} that starts at the top-left corner of
	 * the specified rectangle and ends at the bottom-right corner.
	 * 
	 * @param bounds the bounding rectangle of the line
	 */
    public LineShape(RectF bounds)
    {
        setBounds(bounds);
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    @Override
    public void draw(Canvas canvas)
    {
        Paint paint = getPaint();
        RectF bounds = getBounds();
        canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom,
            paint);
    }


    // ----------------------------------------------------------
    @Override
    public boolean contains(float x, float y)
    {
        float[] point = inverseTransformPoint(x, y);
        float nx = point[0];
        float ny = point[1];

        float tolerance = (float) getStrokeWidth() + 0.5f;

        RectF bounds = getBounds();

        float x1 = bounds.left;
        float y1 = bounds.top;

        float x2 = bounds.right;
        float y2 = bounds.bottom;

        float tSquared = tolerance * tolerance;

        if (distanceSquared(nx, ny, x1, y1) < tSquared) return true;
        if (distanceSquared(nx, ny, x2, y2) < tSquared) return true;

        if (nx < Math.min(x1, x2) - tolerance) return false;
        if (nx > Math.max(x1, x2) + tolerance) return false;
        if (ny < Math.min(y1, y2) - tolerance) return false;
        if (ny > Math.max(y1, y2) + tolerance) return false;

        if (x1 - x2 == 0 && y1 - y2 == 0) return false;

        float u = ((nx - x1) * (x2 - x1) + (ny - y1) * (y2 - y1))
            / distanceSquared(x1, y1, x2, y2);

        return distanceSquared(nx, ny, x1 + u * (x2 - x1), y1 + u * (y2 - y1))
            < tSquared;
    }


    // ----------------------------------------------------------
    private static float distanceSquared(
        float x1, float y1, float x2, float y2)
    {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }
}
