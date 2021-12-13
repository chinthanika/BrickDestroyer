package com.BrickDestroyModel;

/**
 * This is the <code>Model</code> for the <code>HomeMenu</code>, which renders the introductory screen.
 * All fields that maintain a state are held.
 */

public class HomeMenuModel {

    private boolean startClicked;
    private boolean menuClicked;

	public boolean isStartClicked() {
		return startClicked;
	}

	public void setStartClicked(boolean startClicked) {
		this.startClicked = startClicked;
	}

	public boolean isMenuClicked() {
		return menuClicked;
	}

	public void setMenuClicked(boolean menuClicked) {
		this.menuClicked = menuClicked;
	}

}
