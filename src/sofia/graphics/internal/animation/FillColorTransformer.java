package sofia.graphics.internal.animation;

import sofia.graphics.Color;
import sofia.graphics.FillableShape;
import sofia.graphics.PropertyTransformer;

// ----------------------------------------------------------
public class FillColorTransformer implements PropertyTransformer
{
    private FillableShape shape;
    private Color start;
    private Color end;


    // ----------------------------------------------------------
    public FillColorTransformer(FillableShape shape, Color end)
    {
        this.shape = shape;
        this.end = end;
    }


    // ----------------------------------------------------------
    public void onStart()
    {
        start = shape.getFillColor();
    }


    // ----------------------------------------------------------
    public void transform(float t)
    {
        int alpha = Math.max(0, Math.min(255,
            (int) (start.alpha() + (end.alpha() - start.alpha()) * t)));
        int red = Math.max(0, Math.min(255,
            (int) (start.red() + (end.red() - start.red()) * t)));
        int green = Math.max(0, Math.min(255,
            (int) (start.green() + (end.green() - start.green()) * t)));
        int blue = Math.max(0, Math.min(255,
            (int) (start.blue() + (end.blue() - start.blue()) * t)));

        shape.setFillColor(Color.rgb(red, green, blue, alpha));
    }
}