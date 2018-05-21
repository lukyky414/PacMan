package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;

public class TextureUnique implements iTexturable {
	private Texture texture;


	public TextureUnique(String internalPath){
		this.texture = new Texture(internalPath);
	}

	@Override
	public Texture getTexture(float delta) {
		return this.texture;
	}
}
