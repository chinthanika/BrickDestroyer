package com.Objects;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Inherits and implements abstract methods from the <code>Brick</code> Class.
 * This can create a <code>Crack</code> if the impact of the <code>Ball</code> is insufficient to break it.
 */

public class CementBrick extends Brick {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;
    private static final int CEMENT_STRENGTH = 2;
    
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);

    private Crack crack;
    private Shape brickFace;

    /**
     * @param point The <code>Point</code> position of the <code>CementBrick</code> on the <code>GameBoard</code>.
     * @param size The <code>Dimension</code>s of the <code>Brick</code>.
     */
    
    public CementBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir, this.brickFace.getBounds());
            updateBrick();
            return false;
        }
        return true;
    }


    @Override
    public Shape getBrick() {
        return brickFace;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
