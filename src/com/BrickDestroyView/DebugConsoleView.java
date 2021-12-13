package com.BrickDestroyView;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import com.Objects.Wall;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This is the <code>View</code> for the <code>DebugConsole</code>, which allows the user to skip levels and change the number of balls.
 * All requisite sliders, buttons, etc are rendered to the <code>JPanel</code>.
 * The listener in the <code>DebugConsole</code>'s <code>Controller</code> listens to this class.
 */

public class DebugConsoleView extends JPanel {

	private static final long serialVersionUID = 1L;


	private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    /**
     * @param wall The <code>Wall</code> in which the <code>Ball</code> and <code>Player</code> are instantiated.
     */
    
    public DebugConsoleView(Wall wall){

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
