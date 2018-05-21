package fr.univ_lorraine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.univ_lorraine.PacmanGame;

public class MainMenuScreen implements Screen {
	final PacmanGame game;

	public MainMenuScreen(PacmanGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		SpriteBatch batch = new SpriteBatch();
		BitmapFont font = new BitmapFont();

		batch.begin();
		font.draw(batch, "Welcome to PacMan!!! ", 100, 400);
		font.draw(batch, "Tap anywhere to begin!", 100, 350);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}



	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() { }

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}