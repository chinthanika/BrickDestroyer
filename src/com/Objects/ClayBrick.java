package com.Objects;

import java.awt.*;
import java.awt.Point;

/**
 * Inherits and implements abstract methods from the <code>Brick</code> Class.
 */

public class ClayBrick extends Brick {

    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * @param point The <code>Point</code> position of the <code>CementBrick</code> on the <code>GameBoard</code>.
     * @param size The <code>Dimension</code>s of the <code>Brick</code>.
     */
    
    public ClayBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}
