package com.BrickDestroyController;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.BrickDestroyModel.HomeMenuModel;
import com.BrickDestroyView.HomeMenuView;
import com.OG.GameFrame;

public class HomeMenuController implements MouseListener, MouseMotionListener {
	
	private HomeMenuView view;
	private HomeMenuModel model;
	private GameFrame owner;

	public HomeMenuController(GameFrame owner,Dimension area) {
		
		this.owner = owner;
		model = new HomeMenuModel();
		view = new HomeMenuView(model, owner, area);

        view.addMouseListener(this);
        view.addMouseMotionListener(this);
	}

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.getStartButton().contains(p)){
           owner.enableGameBoard();

        }
        else if(view.getMenuButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.getStartButton().contains(p)){
            model.setStartClicked(true);
            view.repaint(view.getStartButton().x,view.getStartButton().y,view.getStartButton().width+1,view.getStartButton().height+1);

        }
        else if(view.getMenuButton().contains(p)){
            model.setMenuClicked(true);
            view.repaint(view.getMenuButton().x,view.getMenuButton().y,view.getMenuButton().width+1,view.getMenuButton().height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(model.isStartClicked() ){
            model.setStartClicked(false);
            view.repaint(view.getStartButton().x,view.getStartButton().y,view.getStartButton().width+1,view.getStartButton().height+1);
        }
        else if(model.isMenuClicked()){
            model.setMenuClicked(false);
            view.repaint(view.getMenuButton().x,view.getMenuButton().y,view.getMenuButton().width+1,view.getMenuButton().height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.getStartButton().contains(p) || view.getMenuButton().contains(p))
            view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            view.setCursor(Cursor.getDefaultCursor());

    }

	public HomeMenuView getView() {
		return view;
	}

	public void setView(HomeMenuView view) {
		this.view = view;
	}
}
