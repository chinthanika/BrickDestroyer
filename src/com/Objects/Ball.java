package com.Objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This represents a single <code>Ball</code>, which can be instantiated and rendered on the screen.
 * The object with which the <code>Player</code> interacts in the game to destroy the <code>Brick</code>s.
 */

abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * @param center The <code>Point2D</code> coordinates of the center of the <code>Ball</code>.
     * @param radiusX The radius of the <code>Ball</code> along the x-axis.
     * @param radiusY The radius of the <code>Ball</code> along the x-axis.
     * @param inner The fill <code>Color</code> of the <code>Ball</code>.
     * @param border The border <code>Color</code> of the <code>Ball</code>. 
     */
    
    public Ball(Point2D center,int radiusX,int radiusY,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusY / 2));
        down.setLocation(center.getX(),center.getY()+(radiusY / 2));

        left.setLocation(center.getX()-(radiusX /2),center.getY());
        right.setLocation(center.getX()+(radiusX /2),center.getY());


        ballFace = makeBall(center,radiusX,radiusY);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }
    
    public Point2D getUp() {
    	return up;
    }
    
    public Point2D getDown() {
    	return down;
    }
    
    public Point2D getLeft() {
    	return left;
    }
    
    public Point2D getRight() {
    	return right;
    }
    
    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


}
