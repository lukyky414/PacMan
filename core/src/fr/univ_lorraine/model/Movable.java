package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

public abstract class Movable extends GameElement{
	public final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3, NOTHING = 4;
	public final static Vector2 vUP = 	 new Vector2(0f, 0.0625f);
	public final static Vector2 vRIGHT = new Vector2(0.0625f, 0f);
	public final static Vector2 vDOWN =  new Vector2(0f, -0.0625f);
	public final static Vector2 vLEFT =  new Vector2(-0.0625f, 0f);
	private int direction = RIGHT;
	private boolean passThrougGate = false;
	private Vector2 SpawnPos;

	protected float tickSpeed;
	private float deltaT = 0f;

	public float lastChangeDir = 0f;


	public int getdirection(){ return this.direction;}
	protected void setDirection(int direction){ this.direction = direction;}

	public Movable(Vector2 pos, World world, float speed){
		super(pos,world);
		SpawnPos = new Vector2(pos);
		setSpeed(speed);
	}

	protected void setSpeed(float speed){
		this.tickSpeed =  1 / (speed * 16);
	}


	abstract void changeDir(int currX, int currY);
	abstract void changeState();
	public abstract void afterReset();

	public void reset(){
		this.pos = new Vector2(SpawnPos);
		this.afterReset();
	}

	@Override
	public void move(float delta){
		deltaT+=delta;
		while(deltaT > tickSpeed) {
			deltaT-=tickSpeed;
			int currX = (int) this.pos.x;
			int currY = (int) this.pos.y;
			boolean stop = false;

			changeState();

			if (this.pos.x % 1 == 0 && this.pos.y % 1 == 0) {//position entiere:
				changeDir(currX, currY);

				int nextx = getNextX(currX, this.getdirection());
				int nexty = getNextY(currY, this.getdirection());

				int type = this.world.getMaze().getMap(nextx, nexty);
				if (type == 0)
					stop = true;
				if (this.direction == NOTHING)
					stop = true;

				int typeActuel = this.world.getMaze().getMap((int) this.pos.x, (int) this.pos.y);
				if (type == 3 && typeActuel != 3 && !passThrougGate)
					stop = true;
			}

			if (!stop)
				switch (direction) {
					case UP:
						this.pos.add(vUP);
						if (this.pos.y > this.world.getHeight() - 1)
							this.pos.y = 0;
						break;
					case RIGHT:
						this.pos.add(vRIGHT);
						if (this.pos.x > this.world.getWidth() - 1)
							this.pos.x = 0;
						break;
					case DOWN:
						this.pos.add(vDOWN);
						if (this.pos.y < 0)
							this.pos.y = this.world.getHeight() - 1;
						break;
					case LEFT:
						this.pos.add(vLEFT);
						if (this.pos.x < 0)
							this.pos.x = this.world.getWidth() - 1;
						break;
				}
		}
	}

	protected void imADeadGhost(){
		passThrougGate = true;
	}
	protected void imALivingGhost(){
		passThrougGate = false;
	}

	protected int getNextX(int currX, int direction){
		switch (direction) {
			case RIGHT:
				currX++;
				if(currX > this.world.getMaze().getWidth()-1)
					currX = 0;
				break;
			case LEFT:
				currX--;
				if(currX < 0)
					currX = this.world.getMaze().getWidth()-1;
				break;
		}
		return currX;
	}

	protected int getNextY(int currY, int direction){
		switch (direction) {
			case UP:
				currY++;
				if(currY > this.world.getMaze().getHeight()-1)
					currY = 0;
				break;
			case DOWN:
				currY--;
				if(currY < 0)
					currY = this.world.getMaze().getHeight()-1;
				break;
		}
		return currY;
	}
}
