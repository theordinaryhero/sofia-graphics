/*
 This file is part of the Greenfoot program.
 Copyright (C) 2005-2009,2010  Poul Henriksen and Michael Kolling

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

 This file is subject to the Classpath exception as provided in the
 LICENSE.txt file that accompanied this code.
 */
package sofia.graphics.collision;

import sofia.graphics.Shape;

/**
 * Checks a collision against a point.
 *
 * @author Poul Henriksen
 */
public class PointCollisionQuery implements CollisionQuery
{
    private float x;
    private float y;
    private Class<?> cls;

    /**
     * Set the point collision query parameters.
     * @param x   The X co-ordinate (in pixels)
     * @param y   The Y co-ordinate (in pixels)
     * @param cls   The class of actors to locate. If null, locate any actor.
     */
    public void init(float x, float y, Class<?> cls)
    {
        this.x = x;
        this.y = y;
        this.cls = cls;
    }

    public boolean checkCollision(Shape shape)
    {
        if (cls != null && !cls.isInstance(shape)) {
            return false;
        }
        return shape.contains(x, y);
    }
}
