package fr.univ_lorraine;

import com.badlogic.gdx.Game;
import fr.univ_lorraine.screens.GameScreen;
import fr.univ_lorraine.screens.MainMenuScreen;

public class PacmanGame extends Game {

	@Override
	public void create(){
		//this.setScreen(new MainMenuScreen(this));
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render() {
		super.render(); //important!
	}
}
