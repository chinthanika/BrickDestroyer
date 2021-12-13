/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.BrickDestroyModel;

import javax.swing.*;

import java.awt.*;

import com.BrickDestroyView.DebugConsoleView;
import com.BrickDestroyView.GameBoardView;
import com.OG.Wall;

public class DebugConsoleModel extends JDialog{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final String TITLE = "Debug Console";


    private JFrame owner;

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
