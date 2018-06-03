package fr.univ_lorraine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.univ_lorraine.Input.MonEcouteur;
import fr.univ_lorraine.PacmanGame;
import fr.univ_lorraine.model.World;
import fr.univ_lorraine.view.TextureFactory;
import fr.univ_lorraine.view.WorldRenderer;

public class WinScreen implements Screen {
	final PacmanGame game;
	private boolean isTouched;
	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;
	private BitmapFont font;

	private Texture mainscreen;

	private int score;

	public WinScreen(PacmanGame game, int score) {
		isTouched = true;
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();

		this.score = score;

		mainscreen = new Texture("images/mainscreen.png");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(mainscreen, 0, 100,
				mainscreen.getWidth()/2,
				mainscreen.getHeight()/2);

		String txt = "GG you have scored " + score;

		font.draw(batch, txt,
				mainscreen.getWidth()/4 - 80,
				70);
		batch.end();

		if (Gdx.input.isTouched()) {
			if(!isTouched) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
		}else {
			isTouched = false;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.graphics.setDisplayMode(
				mainscreen.getWidth()/2,
				mainscreen.getHeight()/2 + 100,
				false);

		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(
				mainscreen.getWidth()/2,
				mainscreen.getHeight()/2 + 100,
				camera);
		this.viewport.apply();
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}