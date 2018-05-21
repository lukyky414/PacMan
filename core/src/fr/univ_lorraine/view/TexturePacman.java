package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;
import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.model.Pacman;

public class TexturePacman implements iTexturable {
	private Pacman pacman;
	private float deltaT = 0.0f, seuil;
	private int step = 0;

	private Texture[] up, right, down, left;


	public TexturePacman(Pacman pacman, float seuil){
		this.pacman = pacman;
		this.seuil = seuil;

		up = new Texture[]{
				new Texture("images/pacmanUp-2.png"),
				new Texture("images/pacmanUp.png"),
				new Texture("images/pacman-3.png")
		};

		right = new Texture[]{
				new Texture("images/pacmanRight-2.png"),
				new Texture("images/pacmanRight.png"),
				new Texture("images/pacman-3.png")
		};

		down = new Texture[]{
				new Texture("images/pacmanDown-2.png"),
				new Texture("images/pacmanDown.png"),
				new Texture("images/pacman-3.png")
		};

		left = new Texture[]{
				new Texture("images/pacmanLeft-2.png"),
				new Texture("images/pacmanLeft.png"),
				new Texture("images/pacman-3.png")
		};
	}


	@Override
	public Texture getTexture(float delta) {
		this.deltaT += delta;
		if(this.deltaT > this.seuil) {
			this.deltaT = 0.0f;
			this.step++;
			if(this.step > 2) this.step = 0;
		}


		switch(pacman.getdirection()){
			case Movable.UP:
				return up[this.step];
			case Movable.RIGHT:
				return right[this.step];
			case Movable.DOWN:
				return down[this.step];
			case Movable.LEFT:
				return left[this.step];

			default:
				return down[this.step]; // should not happen.
		}
	}
}
