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


	//Delta = le temps qu'il a fallu pour que tout soit rendered comme il faut
	// = Temps d'une frame entière
	public void render(float delta) {
		//Batch dessine des rectangles aux positions demandés par le GameElement
		this.batch.begin();

		//Il dessine les rectangles (TextureFactory se charge des dessins) à l'endroit où le GameElement le dit
		for (GameElement element : this.world)
			this.batch.draw(
					TextureFactory.getInstance().getTexture(element.getClass(), delta),
					element.getPosition().x * GameScreen.PPUX,
					element.getPosition().y * GameScreen.PPUY,
					element.getSize().x * GameScreen.PPUX,
					element.getSize().y * GameScreen.PPUY);

		//Ensuite il termine et redessinera à chaque fois qu'il y a un mouvement
		//C'est delta qui fera en sorte que le mouvement soit fluide
		this.batch.end();
	}

	public void move(float delta){
		for (GameElement element : this.world)
			element.move(delta);
	}
}
