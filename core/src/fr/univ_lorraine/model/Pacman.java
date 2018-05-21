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
		int x = (int) this.pos.x;
		int y = (int) this.pos.y;
		int type;

		if(this.pos.x % 1 == 0 && this.pos.y % 1 == 0) {//position entiere:
			switch(nextDir){
				case UP:
					type = this.world.getMaze().getMap(x, y+1);
					if(type == 1 || type == 2)
						this.setDirection(UP);
					break;
				case RIGHT:
					type = this.world.getMaze().getMap(x+1, y);
					if(type == 1 || type == 2)
						this.setDirection(RIGHT);
					break;
				case DOWN:
					type = this.world.getMaze().getMap(x, y-1);
					if(type == 1 || type == 2)
						this.setDirection(DOWN);
					break;
				case LEFT:
					type = this.world.getMaze().getMap(x-1, y);
					if(type == 1 || type == 2)
						this.setDirection(LEFT);
					break;
			}
		}
	}
}
