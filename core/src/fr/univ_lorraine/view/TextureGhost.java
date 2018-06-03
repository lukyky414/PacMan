package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;
import fr.univ_lorraine.model.Ghost;

public class TextureGhost implements iTexturable{


	private Ghost ghost;

	private Texture[] textures;


	public TextureGhost(Ghost ghost, String texutreAlive){
		this.ghost = ghost;
		textures = new Texture[]{
				new Texture(texutreAlive),
				new Texture("images/ghostEscaping.png"),
				new Texture("images/ghostDead.png"),
		};

	}

	public Texture getTexture(float delta) {
		return textures[ghost.getEtat()];
	}
}
