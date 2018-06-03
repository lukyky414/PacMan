package fr.univ_lorraine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

	public final static float FRAME = 0.03125f;
	public final static int PPUX = 16, PPUY = 16;

	private Texture live;
	private Texture died;
	private Texture title;

	private	GestureDetector gd;

	private BitmapFont font;

	public int score;
	public int lives = 3;


	private float deltaT = 0.0f;
	public final static float TIMETOSCORE = 1f;

	public GameScreen(PacmanGame game) {
		this.game = game;
		//this.show() est executé automatiquement.

		font = new BitmapFont();

		live = new Texture("images/pacmanRight-2.png");
		died = new Texture("images/death.png");
		title = new Texture("images/title.png");
	}


	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Color c = batch.getColor();
		batch.setColor(c.r, c.g, c.b, 1f); //set alpha to 1

		if(lives > 0)
			this.move(delta);

		this.KeyBoardListener();


		batch.begin();
		font.draw(batch, "Score: " + this.score, 4, ((this.world.getHeight() + 1) * PPUY) - 3);
		int titleSizeY = PPUY;
		int titleSizeX = (title.getWidth() * PPUY) / title.getHeight(); //Pour garder les proportions;
		int titlePosY = this.world.getHeight() * PPUY;
		int titlePosX = (this.world.getWidth() / 2) * PPUY - (titleSizeX / 2);

		batch.draw(title,titlePosX, titlePosY, titleSizeX, titleSizeY);

		for (int x = 0; x < lives; x++) {
			batch.draw(live,
					(this.world.getWidth() - x - 1) * GameScreen.PPUX,
					(this.world.getHeight()) * GameScreen.PPUY,
					PPUX,
					PPUY);
		}

		batch.end();

		batch.setProjectionMatrix(camera.combined);
		this.worldRenderer.render(delta);
		if(this.lives == 0) {
			batch.begin();


			batch.draw(died,
					0,
					0,
					this.world.getWidth() * PPUX,
					this.world.getHeight() * PPUY);

			batch.end();
			if (Gdx.input.isTouched()) {
				game.setScreen(new MainMenuScreen(game));
				dispose();
			}
		}
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
		this.worldRenderer.move(delta);
		this.postMove(delta);
	}

	private void postMove(float delta){
		deltaT += delta;
		while(deltaT > TIMETOSCORE){
			deltaT -= TIMETOSCORE;
			score++;
		}
		//Test pour les pellets
		int pacX = (int) (world.getPacman().getPosition().x + .5f);
		int pacY = (int) (world.getPacman().getPosition().y + .5f);

		int ghX, ghY ;

		//Manger un pellet
		GameElement element = world.getMaze().get(pacX,pacY);
		if(element != null && element.getClass() == Pellet.class){
			score += 3;
			world.getMaze().delete(pacX, pacY);
			world.pelletNumber--;

			if(world.pelletNumber <= 0){
				game.setScreen(new WinScreen(game, score));
				dispose();
				return;
			}
		}


		//Manger un super pellet
		for(int x= 0; x < world.getSuperPellet().size(); x++){
			SuperPellet p = world.getSuperPellet().get(x);
			if(p.getPosition().equals(this.world.getPacman().getPosition())) {
				world.getSuperPellet().remove(x);

				for(Ghost fantome : this.world.getGhost())
					fantome.fear();
			}
		}

		//Tomber sur un fantome
		for(Ghost fantome : this.world.getGhost()){
			ghX = (int) (fantome.getPosition().x + .5f);
			ghY = (int) (fantome.getPosition().y + .5f);

			if(pacX == ghX && pacY == ghY) {
				switch (fantome.getEtat()){
					case Ghost.POURSUITE:
						this.death();
						break;
					case Ghost.FUITE:
						score+=15;
						fantome.kill();
						break;
				}
			}
		}
	}

	private void death(){
		this.lives--;

		for(Ghost fantome : this.world.getGhost()){
			fantome.reset();
		}

		this.world.getPacman().reset();
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

		Gdx.graphics.setDisplayMode(
				this.world.getWidth() * PPUX,
				(this.world.getHeight() + 1)*PPUY,
				false);

		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(
				this.world.getWidth() * PPUX,
				(this.world.getHeight() + 1)*PPUY,
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