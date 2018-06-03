package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;
import fr.univ_lorraine.model.Ghost;

public class TextureGhost implements iTexturable{


	private Ghost ghost;
	float deltaT = 0f;

	boolean switcher = true;

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
		if(ghost.getEtat() == Ghost.FUITE){
			deltaT += delta;
			float rest = ghost.getFearcooldown();

			if(rest < 1){
				if(deltaT > .1f){
					deltaT = 0f;
					switcher = !switcher;
				}
			}
			else if(rest < 3){
				if(deltaT > .2f){
					deltaT = 0f;
					switcher = !switcher;
				}
			}
			else if(rest < 5){
				if(deltaT > .5f){
					deltaT = 0f;
					switcher = !switcher;
				}
			}
			else if(rest < 10) {
				if (deltaT > 1f) {
					deltaT = 0f;
					switcher = !switcher;
				}
			}
			else
				switcher = true;


			return textures[switcher? Ghost.FUITE : Ghost.POURSUITE];
		}
		else {
			return textures[ghost.getEtat()];
		}
	}
}
