package fr.univ_lorraine.Iterators;

import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.Maze;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MazeIterator implements Iterator<GameElement> {
	private Maze maze;
	int x, y;

	public MazeIterator(Maze maze){
		this.maze = maze;
		x = y = 0;
		this.init();
	}

	private void init(){
		while(maze.get(x,y) == null && this.hasNext()){
			x = (x+1) % this.maze.getWidth();
			if(x == 0) y++;
		}
	}

	@Override
	public boolean hasNext(){
		return (this.y < this.maze.getHeight());// && (this.j < this.maze.getWidth());// <-- THIS WILL NETHER HAPPEN
	}

	@Override
	public GameElement next(){
		if(!this.hasNext()) throw new NoSuchElementException("No more game elements.");

		GameElement ge = this.maze.get(x,y);

		//boucle, car on ne récupère pas le sol. Juste les "pellets" et les murs.
		//Passe au prochain element dans le maze (ou arrive a la fin) pour le prochain next.
		do{
			x = (x+1) % this.maze.getWidth();
			if(x == 0) y++;
		}while(this.hasNext() && maze.get(x,y) == null);

		return ge;
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
