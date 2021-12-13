package com.BrickDestroyController;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import com.BrickDestroyModel.GameBoardModel;
import com.BrickDestroyView.GameBoardView;

/**
 * This is the <code>Controller</code> for the <code>GameBoard</code>, which renders the screen in which the user will play the game.
 * All Key and Mouse Events that occur in the <code>GameBoardView</code>, the <code>GameBoard</code>'s <code>View</code>, are handled.
 */

public class GameBoardController implements KeyListener, MouseListener, MouseMotionListener {
	
	private GameBoardView view;
	private GameBoardModel model;

	/**
	 * @param owner The <code>JFrame</code> in which the game will be played.
	 */
	
	public GameBoardController(JFrame owner) {
		model = new GameBoardModel(this);
		view = new GameBoardView (owner, this);
		
        view.addKeyListener(this);
        view.addMouseListener(this);
        view.addMouseMotionListener(this);
	}
	
	@Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                model.getWall().getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                model.getWall().getPlayer().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                model.setShowPauseMenu(!getModel().isShowPauseMenu());
                view.repaint();
                model.getGameTimer().stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!model.isShowPauseMenu())
                    if(model.getGameTimer().isRunning())
                        model.getGameTimer().stop();
                    else
                        model.getGameTimer().start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    view.getDebugConsole().setVisible(true);
            default:
                model.getWall().getPlayer().stop();
        }
    }
	
    @Override
    public void keyReleased(KeyEvent keyEvent) {
    	model.getWall().getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(!model.isShowPauseMenu())
            return;
        if(view.getContinueButtonRect().contains(point)){
            model.setShowPauseMenu(false);
            view.repaint();
            model.getGameTimer().start();
        }
        else if(view.getRestartButtonRect().contains(point)){
            model.setMessage("Restarting Game. Press SPACE to start.");
            model.getWall().ballReset();
            model.getWall().wallReset();
            model.setShowPauseMenu(false);
            view.repaint();
        }
        else if(view.getExitButtonRect().contains(point)){
            System.exit(0);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.getExitButtonRect() != null && getModel().isShowPauseMenu()) {
            if (view.getExitButtonRect().contains(p) || view.getContinueButtonRect().contains(p) || view.getRestartButtonRect().contains(p))
                view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                view.setCursor(Cursor.getDefaultCursor());
        }
        else{
            view.setCursor(Cursor.getDefaultCursor());
        }
    }
    @Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    public void onLostFocus(){
        model.getGameTimer().stop();
        model.setMessage("Focus Lost. Press SPACE to Continue.");
        view.repaint();
    }

	public GameBoardView getView() {
		return view;
	}

	public GameBoardModel getModel() {
		return model;
	}
}
