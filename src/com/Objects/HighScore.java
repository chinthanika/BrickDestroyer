package com.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Intended to create a permanent HighScore list for the game.
 * Could not be fully implemented due to time constraints.
 */

public class HighScore {
	
	private int highScore;
	private String highScoreName;
	
	public HighScore() {
		
		highScore = 0;
		
		highScoreName = "BrickDestroy_HighScore.txt";
		
		try {
			File highScoreHandle = new File(highScoreName);
		      if (highScoreHandle.createNewFile()) {
		    	  
		    	  System.out.println("Created a new file. HighScore = " + highScore);
		    	  } else {
		    		  System.out.println("Current HighScore: " + highScore);
		    	
		    	  }
		      } catch (IOException e) {
		    	  System.out.println("An error occurred.");
		    	  e.printStackTrace();
		      }
	}
	
	public void updateScore (int newScore) {
		
		setHighScore(newScore);
		
	    try {
	        FileWriter highScoreWriter = new FileWriter(highScoreName, true);
	        
	        highScoreWriter.write("\n" + highScore);
	        highScoreWriter.close();
	        
	        System.out.println("Successfully wrote to the file.");
	      
	    } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	 }
	
	public ArrayList <Integer> getScores () {
		ArrayList <Integer> highScoreList = new ArrayList <Integer> ();
		
		try {
		      File highScoreHandle = new File(highScoreName);
		      Scanner highScoreRead = new Scanner(highScoreHandle);
		      while (highScoreRead.hasNext()) {
		    	  if (highScoreRead.hasNextInt()) {
		    	
		    		  highScoreList.add(highScoreRead.nextInt());
		    		  System.out.println("Current HighScore: " + highScore);
		    	  }
		      }
		      
		      highScoreRead.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return highScoreList;
	}

	public int getHighScore() {
		return highScore;
	}
	
	public void setHighScore (int newScore) {
		highScore = newScore;
	}
}