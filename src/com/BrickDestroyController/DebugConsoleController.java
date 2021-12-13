package com.BrickDestroyController;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

//import com.OG.BallModel;
import com.BrickDestroyModel.DebugConsoleModel;
import com.BrickDestroyView.DebugConsoleView;
import com.BrickDestroyView.GameBoardView;
import com.Objects.Ball;
import com.Objects.Wall;

/** This is the <code>Controller</code> for the <code>DebugConsole</code>, which allows the user to skip levels and change the number of balls.
 * All Window Events that occur in the <code>DebugConsoleView</code>, the <code>DebugConsle</code>'s <code>View</code>, are handled.
 */

public class DebugConsoleController implements WindowListener{
	
	private DebugConsoleView view;
	private DebugConsoleModel model;
	private GameBoardView gameBoard;
	private Wall wall;

/**
 * @param owner The <code>JFrame</code> in which the game will be played.
 * @param wall The <code>Wall</code> in which the <code>Ball</code> and <code>Player</code> are instantiated.
 * @param gameBoard The <code>GameBoardView</code> in which the DebugConsole will be rendered.
 */
	public DebugConsoleController(JFrame owner,Wall wall,GameBoardView gameBoard) {
		
		this.gameBoard = gameBoard;
		this.wall = wall;
		view = new DebugConsoleView(wall);
		model = new DebugConsoleModel(view, owner, wall, gameBoard);
		
		model.addWindowListener(this);
	}

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        model.setLocation();
        Ball b = wall.getBall();
        view.setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

	public DebugConsoleView getView() {
		return view;
	}

	public DebugConsoleModel getModel() {
		return model;
	}
}
