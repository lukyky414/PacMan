package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends Movable {

	private int nextDir;

	public Pacman(Vector2 pos, World world){
		super(pos,world, 2f);
		nextDir = RIGHT;
	}

	@Override
	void changeState() {

	}


	public void newDirection(int direction){this.nextDir = direction;}

	@Override
	public void changeDir(int currX, int currY){
		int nextX = getNextX(currX, nextDir);
		int nextY = getNextY(currY, nextDir);
		int type;
		boolean change = true;

		type = this.world.getMaze().getMap(nextX, nextY);
		if(type == 0 || type == 3)
				change = false;

		if(change)
			this.setDirection(nextDir);

	}

	@Override
	public void afterReset() {
		nextDir = RIGHT;
	}


}
