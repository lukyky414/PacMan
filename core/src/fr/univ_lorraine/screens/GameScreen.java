package fr.univ_lorraine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.univ_lorraine.Input.MonEcouteur;
import fr.univ_lorraine.PacmanGame;
import fr.univ_lorraine.model.*;
import fr.univ_lorraine.view.TextureFactory;
import fr.univ_lorraine.view.WorldRenderer;

public class GameScreen implements Screen {
	final PacmanGame game;


	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;

	private World world;
	private WorldRenderer worldRenderer;

	private float deltaT = 0.0f;
	public final static float FRAME = 0.03125f;

	private	GestureDetector gd;

	public static int score;

	public GameScreen(PacmanGame game) {
		this.game = game;
		//this.show() est executé automatiquement.



	}


	@Override
	public void render(float delta) {
		this.move(delta);

		this.KeyBoardListener();


		camera.update();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		this.worldRenderer.render(delta);
	}

	private void KeyBoardListener() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			this.world.getPacman().newDirection(Movable.LEFT);

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			this.world.getPacman().newDirection(Movable.RIGHT);

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			this.world.getPacman().newDirection(Movable.DOWN);

		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			this.world.getPacman().newDirection(Movable.UP);
	}

	private void move(float delta){
		this.deltaT += delta;
		while(deltaT > FRAME){
			deltaT-= FRAME;
			this.worldRenderer.move();
			this.postMove();
		}
	}

	private void postMove(){
		//Test pour les pellets
		int pacX = (int) (world.getPacman().getPosition().x + .5f);
		int pacY = (int) (world.getPacman().getPosition().y + .5f);

		GameElement element = world.getMaze().get(pacX,pacY);
		if(element != null && element.getClass() == Pellet.class){
			score += 1;
			world.getMaze().delete(pacX, pacY);
		}


		if(element != null && element.getClass() == SuperPellet.class){

			world.getMaze().delete(pacX, pacY);
		}
	}



	@Override
	public void resize(int width, int height) {
		viewport.update(width,height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}




	public World getWorld() { return this.world; }

	@Override
	public void show() {
		this.world = new World();

		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(this.world.getWidth() * 16,
				this.world.getHeight()*16,
				camera);
		this.viewport.apply();

		this.worldRenderer = new WorldRenderer(this.world, this.batch);

		TextureFactory.reset(this.world);

		score = 0;
		gd = new GestureDetector(new MonEcouteur(this.world.getPacman()));
		Gdx.input.setInputProcessor(gd);
	}

	@Override
	public void hide() { }

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
}