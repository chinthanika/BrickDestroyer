package com.Objects;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import com.BrickDestroyController.GameBoardController;
import com.BrickDestroyController.HomeMenuController;

/**
 * The <code>JFrame</code> in which all other objects will be rendered.
 * Handles the Window Focus events, the <code>Dimension</code> of the screen and the switch between <code>JComponents</code>.
 */

public class GameFrame extends JFrame implements WindowFocusListener {

	private static final long serialVersionUID = 1L;

	private static final String DEF_TITLE = "Brick Destroy";

    private GameBoardController gameBoard;
    private HomeMenuController homeMenu;

    private boolean gaming;

    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoardController(this);

        homeMenu = new HomeMenuController(this,new Dimension(450,300));

        this.add(homeMenu.getView(),BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu.getView());
        this.add(gameBoard.getView(),BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}