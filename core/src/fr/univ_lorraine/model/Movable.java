package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Movable extends GameElement{
	public final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	public final static Vector2 vUP = 	 new Vector2(0f, 0.0625f);
	public final static Vector2 vRIGHT = new Vector2(0.0625f, 0f);
	public final static Vector2 vDOWN =  new Vector2(0f, -0.0625f);
	public final static Vector2 vLEFT =  new Vector2(-0.0625f, 0f);
	private int direction = RIGHT;
	protected int[] bloquant = new int[]{0,3};


	public int getdirection(){ return this.direction;}
	public void setDirection(int direction){ this.direction = direction;}

	public Movable(Vector2 pos, World world){
		super(pos,world);
	}
	public Movable(Vector2 pos, World world, int[] bloquant){
		super(pos,world);
		this.bloquant = bloquant;
	}

	abstract void changeDir();

	@Override
	public void move(){
		int nextx = (int) this.pos.x;
		int nexty = (int) this.pos.y;
		int type;
		boolean stop = false;

		if(this.pos.x % 1 == 0 && this.pos.y % 1 == 0) {//position entiere:
			changeDir();
			switch (direction) {
				case UP:
					nexty++;
					if(nexty > this.world.getMaze().getHeight()-1)
						nexty = 0;
					break;
				case RIGHT:
					nextx++;
					if(nextx > this.world.getMaze().getWidth()-1)
						nextx = 0;
					break;
				case DOWN:
					nexty--;
					if(nexty < 0)
						nexty = this.world.getMaze().getHeight()-1;
					break;
				case LEFT:
					nextx--;
					if(nextx < 0)
						nextx = this.world.getMaze().getWidth()-1;
					break;
			}

			type = this.world.getMaze().getMap(nextx, nexty);
			for(int non : this.bloquant)
				if(type == non)
					stop = true;
		}

		if(!stop)
			switch(direction){
				case UP:
					this.pos.add(vUP);
					if(this.pos.y > this.world.getHeight()-1)
						this.pos.y = 0;
					break;
				case RIGHT:
					this.pos.add(vRIGHT);
					if(this.pos.x > this.world.getWidth()-1)
						this.pos.x = 0;
					break;
				case DOWN:
					this.pos.add(vDOWN);
					if(this.pos.y < 0)
						this.pos.y = this.world.getHeight()-1;
					break;
				case LEFT:
					this.pos.add(vLEFT);
					if(this.pos.x < 0)
						this.pos.x = this.world.getWidth()-1;
					break;
			}
	}

	public void setBloquant(int[] b){
		bloquant = b;
	}
}
