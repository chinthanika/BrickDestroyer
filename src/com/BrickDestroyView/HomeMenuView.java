package com.BrickDestroyView;

import javax.swing.*;

import com.BrickDestroyModel.HomeMenuModel;
import com.Objects.GameFrame;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * This is the <code>View</code> for the <code>HoemMenu</code>, which renders the screen in which the user will play the game.
 * All requisite components, buttons, etc are rendered to the <code>JComponent</code>.
 * All listeners in the <code>HomeMenu</code>'s <code>Controller</code> listen to this class.
 */

public class HomeMenuView extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;
	private HomeMenuModel model;

	/**
	 * @param model The <code>HomeMenu</code>'s <code>Model</code> Class.
	 * @param owner The <code>JFrame</code> in which the game will be played.
	 * @param area The <code>Dimension</code> of the <code>HomeMenu</code>.
	 */
	
    public HomeMenuView(HomeMenuModel model,GameFrame owner,Dimension area){

    	this.model = model;
        this.setFocusable(true);
        this.requestFocusInWindow();

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        setStartButton(new Rectangle(btnDim));
        setMenuButton(new Rectangle(btnDim));

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,getStartButton().height-2);

    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - getStartButton().width) / 2;
        int y =(int) ((menuFace.height - getStartButton().height) * 0.8);

        getStartButton().setLocation(x,y);

        x = (int)(getStartButton().getWidth() - txtRect.getWidth()) / 2;
        y = (int)(getStartButton().getHeight() - txtRect.getHeight()) / 2;

        x += getStartButton().x;
        y += getStartButton().y + (getStartButton().height * 0.9);




        if(model.isStartClicked()){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(getStartButton());
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(getStartButton());
            g2d.drawString(START_TEXT,x,y);
        }

        x = getStartButton().x;
        y = getStartButton().y;

        y *= 1.2;

        getMenuButton().setLocation(x,y);




        x = (int)(getMenuButton().getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(getMenuButton().getHeight() - mTxtRect.getHeight()) / 2;

        x += getMenuButton().x;
        y += getMenuButton().y + (getStartButton().height * 0.9);

        if(model.isMenuClicked()){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(getMenuButton());
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(getMenuButton());
            g2d.drawString(MENU_TEXT,x,y);
        }

    }


	public Rectangle getStartButton() {
		return startButton;
	}


	public void setStartButton(Rectangle startButton) {
		this.startButton = startButton;
	}


	public Rectangle getMenuButton() {
		return menuButton;
	}


	public void setMenuButton(Rectangle menuButton) {
		this.menuButton = menuButton;
	}


}
