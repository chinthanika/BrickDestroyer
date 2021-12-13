package com.Objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Inherits and implements abstract methods from the <code>Brick</code> Class.
 */

public class SteelBrick extends Brick {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * @param point The <code>Point</code> position of the <code>CementBrick</code> on the <code>GameBoard</code>.
     * @param size The <code>Dimension</code>s of the <code>Brick</code>.
     */
    
    public SteelBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
