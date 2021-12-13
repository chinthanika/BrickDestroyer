
package com.BrickDestroyView;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.BrickDestroyController.DebugConsoleController;
import com.BrickDestroyController.GameBoardController;
import com.BrickDestroyModel.DebugConsoleModel;
import com.Objects.Ball;
import com.Objects.Brick;
import com.Objects.Player;

/**
 * This is the <code>View</code> for the <code>GameBoard</code>, which renders the screen in which the user will play the game.
 * All requisite components, buttons, etc are rendered to the <code>JComponent</code>.
 * All listeners in the <code>GameBoard</code>'s <code>Controller</code> listen to this class.
 */

public class GameBoardView extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;


    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private Rectangle highScoresButtonRect;
    private int strLen;

    private DebugConsoleController debugConsole;
    
	private GameBoardController controller;

	/**
	 * @param owner The <code>JFrame</code> in which the game will be played.
	 * @param controller The <code>GameBoard</code>'s <code>Controller</code> Class.
	 */
	
    public GameBoardView(JFrame owner, GameBoardController controller){
        super();

        this.controller = controller;
        
        strLen = 0;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        this.initialize();
        
        controller.getModel().setMessage("");

        debugConsole = new DebugConsoleController(owner,controller.getModel().getWall(),this);
        //initialize the first level
        controller.getModel().getWall().nextLevel();

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(getDefWidth(),getDefHeight()));
        this.setFocusable(true);
        this.requestFocusInWindow();   
    }


    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(controller.getModel().getMessage(),250,225);

        drawBall(controller.getModel().getWall().getBall(),g2d);

        for(Brick b : controller.getModel().getWall().getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(controller.getModel().getWall().getPlayer(),g2d);

        if(controller.getModel().isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.getInnerColor());
        g2d.fill(s);

        g2d.setColor(Player.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getDefWidth(),getDefHeight());

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(getContinueButtonRect() == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            setContinueButtonRect(menuFont.getStringBounds(CONTINUE, frc).getBounds());
            getContinueButtonRect().setLocation(x,y-getContinueButtonRect().height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(getRestartButtonRect() == null){
            setRestartButtonRect((Rectangle) getContinueButtonRect().clone());
            getRestartButtonRect().setLocation(x,y-getRestartButtonRect().height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(getExitButtonRect() == null){
            setExitButtonRect((Rectangle) getContinueButtonRect().clone());
            getExitButtonRect().setLocation(x,y-getExitButtonRect().height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

	public static int getDefWidth() {
		return DEF_WIDTH;
	}



	public static int getDefHeight() {
		return DEF_HEIGHT;
	}



	public DebugConsoleModel getDebugConsole() {
		return debugConsole.getModel();
	}


	public Rectangle getContinueButtonRect() {
		return continueButtonRect;
	}


	public void setContinueButtonRect(Rectangle continueButtonRect) {
		this.continueButtonRect = continueButtonRect;
	}



	public Rectangle getRestartButtonRect() {
		return restartButtonRect;
	}



	public void setRestartButtonRect(Rectangle restartButtonRect) {
		this.restartButtonRect = restartButtonRect;
	}



	public Rectangle getExitButtonRect() {
		return exitButtonRect;
	}



	public void setExitButtonRect(Rectangle exitButtonRect) {
		this.exitButtonRect = exitButtonRect;
	}
	
	public Rectangle getHighScoresButtonRect() {
		return highScoresButtonRect;
		
	}
	
	public void setHighScoresButtonRect(Rectangle highScoresButtonRect) {
		this.highScoresButtonRect = highScoresButtonRect;
	}

}

