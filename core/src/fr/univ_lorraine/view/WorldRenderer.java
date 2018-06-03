package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.World;
import fr.univ_lorraine.screens.GameScreen;

public class WorldRenderer {
	private World world;
	private SpriteBatch batch;


	public WorldRenderer(World world, SpriteBatch batch){
		this.world = world;
		this.batch = batch;
	}


	public void render(float delta) {
		this.batch.begin();

		for (GameElement element : this.world)
			this.batch.draw(
					TextureFactory.getInstance().getTexture(element.getClass(), delta),
					element.getPosition().x * GameScreen.PPUX,
					element.getPosition().y * GameScreen.PPUY,
					element.getSize().x * GameScreen.PPUX,
					element.getSize().y * GameScreen.PPUY);

		this.batch.end();
	}

	public void move(float delta){
		for (GameElement element : this.world)
			element.move(delta);
	}
}
