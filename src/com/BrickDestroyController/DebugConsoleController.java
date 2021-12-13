package com.BrickDestroyController;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.OG.Ball;
import com.BrickDestroyModel.DebugConsoleModel;
import com.BrickDestroyView.DebugConsoleView;
import com.BrickDestroyView.GameBoardView;
import com.OG.Wall;

public class DebugConsoleController implements WindowListener{
	
	private DebugConsoleView view;
	private DebugConsoleModel model;
	private GameBoardView gameBoard;
	private Wall wall;

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
