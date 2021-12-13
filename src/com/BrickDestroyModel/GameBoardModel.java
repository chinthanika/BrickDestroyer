package com.BrickDestroyModel;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Timer;

import com.BrickDestroyController.GameBoardController;
import com.BrickDestroyView.GameBoardView;
import com.Objects.Wall;

/**
 * This is the <code>Model</code> for the <code>GameBoard</code>, which renders the screen in which the user will play the game.
 * All fields that maintain a state are held.
 */

public class GameBoardModel {

    private Timer gameTimer;
    
    private boolean showPauseMenu;
    
    private Wall wall;

    private String message;

    /**
     * @param controller The <code>GameBoard</code>'s <code>Controller</code>.
     */
    
    public GameBoardModel (GameBoardController controller) {
    	
    	showPauseMenu = false;
    	
        wall = new Wall(new Rectangle(0,0,GameBoardView.getDefWidth(),GameBoardView.getDefHeight()),30,3,6/2,new Point(300,430));
        
        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = "Game over";
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }
           controller.getView().repaint();
        });
    }
    
	public boolean isShowPauseMenu() {
		return showPauseMenu;
	}

	public void setShowPauseMenu(boolean showPauseMenu) {
		this.showPauseMenu = showPauseMenu;
	}

	public Timer getGameTimer() {
		return gameTimer;
	}

	public Wall getWall() {
		return wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public void setMessage(String message) {
		this.message = message; 
	}
	
	public String getMessage() {
		return message;
	}

}
