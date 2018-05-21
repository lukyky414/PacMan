package fr.univ_lorraine.Iterators;

import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.SuperPellet;
import fr.univ_lorraine.model.World;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WorldIterator implements Iterator<GameElement> {
	private World world;
	private Iterator<GameElement> mazeIterator;
	private Iterator<SuperPellet> superPelletIterator;
	private int i;
	private final static int MAX = 3;

	public WorldIterator(World world){
		this.world = world;
		this.mazeIterator = this.world.getMaze().iterator();
		this.superPelletIterator = this.world.getSuperPellet().iterator();
		this.i = 0;
	}

	@Override
	public boolean hasNext(){
		return (this.i < MAX);
	}

	@Override
	public GameElement next() {
		if(!hasNext()) throw new NoSuchElementException("No more game elements.");


		switch(this.i){
			case 0: //Maze
				if(!this.mazeIterator.hasNext())
					this.i++;
				else
					return this.mazeIterator.next();

			case 1: //SuperPellet
				if(!this.superPelletIterator.hasNext())
					this.i++;
				else
					return this.superPelletIterator.next();

			case 2: //PacMan
				i++;
				return this.world.getPacman();

			default:
				throw new NoSuchElementException("No more element in this collection.");
		}
	}

	@Override
	public void remove(){
		//nothing here

		/*On ne gère aucune collection dans cet iterator,
		 * il n'y a donc aucune suppression à faire.
		 * Cette methode n'est censé etre appellée qu'une fois par "next"
		 * et permet de séparer un peu le code.*/
	}
}
