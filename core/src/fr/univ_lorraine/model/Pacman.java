package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends Movable {

	private int nextDir;

	public Pacman(Vector2 pos, World world){
		super(pos,world);
		nextDir = RIGHT;
	}


	public void newDirection(int direction){this.nextDir = direction;}

	@Override
	public void changeDir(){
		int nextx = (int) this.pos.x;
		int nexty = (int) this.pos.y;
		int type;
		boolean change = true;

		switch (nextDir) {
			case UP:
				nexty++;
				if (nexty > this.world.getMaze().getHeight() - 1)
					nexty = 0;
				break;
			case RIGHT:
				nextx++;
				if (nextx > this.world.getMaze().getWidth() - 1)
					nextx = 0;
				break;
			case DOWN:
				nexty--;
				if (nexty < 0)
					nexty = this.world.getMaze().getHeight() - 1;
				break;
			case LEFT:
				nextx--;
				if (nextx < 0)
					nextx = this.world.getMaze().getWidth() - 1;
				break;
		}
		type = this.world.getMaze().getMap(nextx, nexty);
		for(int non : this.bloquant)
			if(type == non)
				change = false;

		if(change)
			this.setDirection(nextDir);

	}
}
