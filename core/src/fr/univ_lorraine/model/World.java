package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.Iterators.WorldIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class World implements Iterable<GameElement> {
	private Pacman pacman;
	private ArrayList<SuperPellet> superPellet = new ArrayList<SuperPellet>();
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	private Maze maze;
	public int pelletNumber = 0;

	public World(){
		this.maze = new Maze(this);
		this.pacman = new Pacman(new Vector2(12,13), this);


		this.ghosts.add(new RedGhost(new Vector2(15, 17), this));
		this.ghosts.add(new PinkGhost(new Vector2(14, 17), this));
		this.ghosts.add(new BlueGhost(new Vector2(13, 17), this));
		this.ghosts.add(new YellowGhost(new Vector2(12, 17), this));



		this.superPellet.add(new SuperPellet(new Vector2(17, 4), this));
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
	public ArrayList<Ghost> getGhost(){return ghosts;}

	public RedGhost getRedGhost(){return (RedGhost) ghosts.get(0);}
	public PinkGhost getPinkGhost(){return (PinkGhost) ghosts.get(1);}
	public BlueGhost getBlueGhost(){return (BlueGhost) ghosts.get(2);}
	public YellowGhost getYellowGhost(){return (YellowGhost) ghosts.get(3);}

	@Override
	public Iterator<GameElement> iterator(){
		return new WorldIterator(this);
	}
}