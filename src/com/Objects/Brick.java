package com.Objects;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *  This represents a single <code>Brick</code>, which can be instantiated and rendered on the screen.
 *  Undergoes a state change whenever it is hit by the <code>Ball</code>.
 */

abstract public class Brick  {

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;
    
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * @param pos The <code>Point</code> position of the <code>Brick</code> on the <code>GameBoard</code>.
     * @param size The <code>Dimension</code>s of the <code>Brick</code>.
     * @param border The border <code>Color</code> of the <code>Brick</code>.
     * @param inner The fill <code>Color</code> of the <code>Brick</code>.
     * @param strength The force (impact) needed to break the <code>Brick</code>.
     */
    
    public Brick( Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();



    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }

}

