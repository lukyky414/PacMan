package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

public abstract class GameElement {
	protected Vector2 pos, size;
	protected World world;


	public GameElement(Vector2 pos, World world){
		this.pos = pos;
		this.world = world;
		this.size = new Vector2(1,1);
	}

	public Vector2 getPosition(){return pos;}
	public Vector2 getSize(){return size;}

	public void move(){}
}
