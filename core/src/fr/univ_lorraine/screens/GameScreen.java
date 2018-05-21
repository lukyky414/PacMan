package fr.univ_lorraine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.univ_lorraine.PacmanGame;
import fr.univ_lorraine.model.World;
import fr.univ_lorraine.view.TextureFactory;
import fr.univ_lorraine.view.WorldRenderer;
import fr.univ_lorraine.Input.MonEcouteur;

import java.util.Arrays;

public class GameScreen implements Screen {
	final PacmanGame game;


	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;

	private World world;
	private WorldRenderer worldRenderer;

	private float deltaT = 0.0f, frame = 0.03125f;

	public GameScreen(PacmanGame game) {
		this.game = game;
		this.world = new World();

		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(this.world.getWidth() * 16,
				this.world.getHeight()*16,
				camera);
		this.viewport.apply();

		this.worldRenderer = new WorldRenderer(this.world, this.batch);

		GestureDetector gd;
		gd = new GestureDetector(new MonEcouteur(this.world.getPacman()));
		Gdx.input.setInputProcessor(gd);
	}


	@Override
	public void render(float delta) {
		this.move(delta);


		camera.update();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		this.worldRenderer.render(delta);
	}

	private void move(float delta){
		this.deltaT += delta;
		while(deltaT > frame){
			deltaT-=frame;
			this.worldRenderer.move();
		}
	}



	@Override
	public void resize(int width, int height) {
		viewport.update(width,height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}




	public World getWorld() { return this.world; }

	@Override
	public void show() { TextureFactory.reset(this.world); }

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
}