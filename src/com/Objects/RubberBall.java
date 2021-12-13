package com.Objects;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Inherits and implements abstract methods from the <code>Ball</code> class.
 */


public class RubberBall extends Ball {


    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    /**
     * @param center The <code>Point2D</code> coordinates of the center of the <code>Ball</code>.
     */
    
    public RubberBall(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


    @Override
    protected Shape makeBall(Point2D center, int radiusX, int radiusY) {

        double x = center.getX() - (radiusX / 2);
        double y = center.getY() - (radiusY / 2);

        return new Ellipse2D.Double(x,y,radiusX,radiusY);
    }
}
