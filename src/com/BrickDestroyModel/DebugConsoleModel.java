
package com.BrickDestroyModel;

import javax.swing.*;

import java.awt.*;

import com.BrickDestroyView.DebugConsoleView;
import com.BrickDestroyView.GameBoardView;
import com.Objects.Wall;

/**
 * This is the <code>Model</code> for the <code>DebugConsole</code>, which allows the user to skip levels and change the number of balls.
 * All fields that maintain a state are held.
 */


public class DebugConsoleModel extends JDialog{

	private static final long serialVersionUID = 1L;


	private static final String TITLE = "Debug Console";


    private JFrame owner;
    /**
     * @param view The <code>DebugConsole</code>'s <code>View</code>.
     * @param owner The <code>JFrame</code> in which the game will be played.
     * @param wall The <code>Wall</code> in which the <code>Ball</code> and <code>Player</code> are instantiated.
     * @param gameBoard The <code>GameBoardView</code> in which the DebugConsole will be rendered.
     */
    
    public DebugConsoleModel(DebugConsoleView view, JFrame owner,Wall wall,GameBoardView gameBoard){
        this.owner = owner;
        initialize();
 
        this.add(view,BorderLayout.CENTER);

        this.pack();
    }

    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
    }


    public void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }

}
