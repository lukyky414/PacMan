package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.World;

public class WorldRenderer {
	private World world;
	private SpriteBatch batch;
	public float ppuX, ppuY;


	public WorldRenderer(World world, SpriteBatch batch){
		this.world = world;
		ppuX = ppuY = 16;
		this.batch = batch;
	}


	public void render(float delta) {
		this.batch.begin();

		for (GameElement element : this.world)
			this.batch.draw(
					TextureFactory.getInstance().getTexture(element.getClass(), delta),
					element.getPosition().x * ppuX,
					element.getPosition().y * ppuY,
					element.getSize().x *ppuX,
					element.getSize().y * ppuY);

		this.batch.end();
	}

	public void move(){
		for (GameElement element : this.world)
			element.move();
	}
}
