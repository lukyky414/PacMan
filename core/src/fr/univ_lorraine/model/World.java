package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.Iterators.WorldIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {
	private Pacman pacman;
	private ArrayList<SuperPellet> superPellet = new ArrayList<SuperPellet>();
	private RedGhost redGhost;
	private Maze maze;

	public World(){
		this.maze = new Maze(this);
		this.pacman = new Pacman(new Vector2(14,7), this);
		this.redGhost = new RedGhost(new Vector2(15, 19), this); //14,17
		this.superPellet.add(new SuperPellet(new Vector2(1,7), this));
		this.superPellet.add(new SuperPellet(new Vector2(26,7), this));
		this.superPellet.add(new SuperPellet(new Vector2(1,28), this));
		this.superPellet.add(new SuperPellet(new Vector2(26,28), this));
	}

	public Maze getMaze(){return maze;}
	public int getWidth(){return maze.getWidth();}
	public int getHeight(){return maze.getHeight();}

	public Pacman getPacman(){return pacman;}
	public ArrayList<SuperPellet> getSuperPellet(){return superPellet;}
	public RedGhost getRedGhost(){return redGhost;}

	@Override
	public Iterator<GameElement> iterator(){
		return new WorldIterator(this);
	}
}