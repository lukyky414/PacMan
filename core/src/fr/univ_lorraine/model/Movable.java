package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Movable extends GameElement{
	public final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	public final static Vector2 vUP = 	 new Vector2(0f, 0.0625f);
	public final static Vector2 vRIGHT = new Vector2(0.0625f, 0f);
	public final static Vector2 vDOWN =  new Vector2(0f, -0.0625f);
	public final static Vector2 vLEFT =  new Vector2(-0.0625f, 0f);
	int direction = RIGHT;

	public int getdirection(){ return this.direction;}
	public void setDirection(int direction){ this.direction = direction;}

	public Movable(Vector2 pos, World world){
		super(pos,world);
	}

	abstract void changeDir();

	@Override
	public void move(){
		changeDir();
		int x = (int) this.pos.x;
		int y = (int) this.pos.y;
		int type;
		boolean stop = false;

		if(this.pos.x % 1 == 0 && this.pos.y % 1 == 0)//position entiere:
			switch(direction){
				case UP:
					type = this.world.getMaze().getMap(x, y+1);
					if(type == 0 || type == 3)
						stop = true;
					break;
				case RIGHT:
					type = this.world.getMaze().getMap(x+1, y);
					if(type == 0 || type == 3)
						stop = true;
					break;
				case DOWN:
					type = this.world.getMaze().getMap(x, y-1);
					if(type == 0 || type == 3)
						stop = true;
					break;
				case LEFT:
					type = this.world.getMaze().getMap(x-1, y);
					if(type == 0 || type == 3)
						stop = true;
					break;
			}

		if(!stop)
			switch(direction){
				case UP:
					this.pos.add(vUP);
					break;
				case RIGHT:
					this.pos.add(vRIGHT);
					break;
				case DOWN:
					this.pos.add(vDOWN);
					break;
				case LEFT:
					this.pos.add(vLEFT);
					break;
			}
	}
}
