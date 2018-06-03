package fr.univ_lorraine.PathFinding;

import com.badlogic.gdx.math.GridPoint2;
import fr.univ_lorraine.model.Maze;

import java.util.LinkedList;
import java.util.Stack;

public class Vague {
    Vague father;
    Maze maze;
    GridPoint2 source, target;

    public Vague(Vague father, GridPoint2 source, GridPoint2 target, Maze maze){
        this.father = father;
        this.maze = maze;
        this.source = source;
        this.target = target;
    }

    public Vague(GridPoint2 source, GridPoint2 target, Maze maze){
        this(null, source, target, maze);
    }

    public GridPoint2 getPos(){return source;}
    public Vague getFather(){return father;}

    public boolean inonder(LinkedList<Vague> tsunami){
        if(source.equals(target))//->.equals test bien les positions?
            return true;

		//chercher cases vides autour,
		//créer une vague avec chaques coordonnées de cases trouvée en tant que source
		GridPoint2[] nextPos = {//up, right, down, left
				new GridPoint2 (source.x, source.y +1),
				new GridPoint2 (source.x+1, source.y),
				new GridPoint2 (source.x, source.y -1),
				new GridPoint2(source.x-1, source.y)
		};

		for(int i = 0; i < 4; i++){
			//!\\Les cases vides autour -> ne pas rechercher en dehors du tableau
			//Si ça dépasse la taille, revenir au début (tp de gauche à droite et inversement, ainsi que haut bas)
			if(nextPos[i].x < 0)
				nextPos[i].x = maze.getWidth()-1;
			if(nextPos[i].x > maze.getWidth()-1)
				nextPos[i].x = 0;

			if (nextPos[i].y > maze.getHeight()-1)
				nextPos[i].y = 0;
			if (nextPos[i].y < 0)
				nextPos[i].y = maze.getHeight()-1;


			int type = maze.getMap(nextPos[i].x, nextPos[i].y);
			if(type != 0)
				tsunami.addLast(new Vague(this, nextPos[i], target, maze));
		}

        return false;
    }

    public Vague getFirstSon(){
        Vague son = this;
        while(father != null && father.getFather() != null){
            son = father;
            father = father.getFather();
        }
        return son;
    }


}
