package fr.univ_lorraine.Input;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Pacman;

import static java.lang.Math.abs;

public class MonEcouteur implements GestureDetector.GestureListener {
	private Pacman pacman;

	public MonEcouteur(Pacman pacman){
		this.pacman = pacman;
	}

	@Override public boolean touchDown(float x, float y, int pointer, int button) {return false; }
	@Override public boolean tap(float x, float y, int count, int button){return false; }//TODO nouvel input
	@Override public boolean longPress(float x, float y){return false; }
	@Override public boolean pan(float x, float y, float deltaX, float deltaY){return false; }
	@Override public boolean panStop(float x, float y, int pointer, int button) {return false;}
	@Override public boolean zoom(float initialDistance, float distance){return false; }
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2){ return false;}

	@Override public boolean fling(float velocityX, float velocityY, int button){
		if(abs(velocityX) > abs(velocityY)){
			if(velocityX > 0)
				pacman.newDirection(Pacman.RIGHT);
			else
				pacman.newDirection(Pacman.LEFT);
		}
		else{
			if(velocityY > 0)
				pacman.newDirection(Pacman.DOWN);
			else
				pacman.newDirection(Pacman.UP);

		}

		return true;
	}
}