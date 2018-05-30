package fr.univ_lorraine.PathFinding;

import com.badlogic.gdx.math.GridPoint2;
import fr.univ_lorraine.model.Maze;

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

    public boolean inonder(Stack<Vague> tsunami){
        if(source.equals(target))//->.equals test bien les positions?
            return true;


        //chercher cases vides autour,
        //créer une vague avec chaques coordonnées de cases trouvée en tant que source

        GridPoint2 left = new GridPoint2(source.x-1, source.y);
        GridPoint2 right = new GridPoint2 (source.x+1, source.y);
        GridPoint2 up = new GridPoint2 (source.x, source.y +1);
        GridPoint2 down = new GridPoint2 (source.x, source.y -1);

        if(maze.getMap(left.x, left.y) == 1 || maze.getMap(left.x, left.y) == 2 || maze.getMap(left.x, left.y) == 3)
            tsunami.push(new Vague(left, target, maze));
        if(maze.getMap(right.x, right.y) == 1 || maze.getMap(right.x, right.y) == 2 || maze.getMap(right.x, right.y) == 3)
            tsunami.push(new Vague(right, target, maze));
        if(maze.getMap(up.x, up.y) == 1 || maze.getMap(up.x, up.y) == 2 || maze.getMap(up.x, up.y) == 3)
            tsunami.push(new Vague(up, target, maze));
        if(maze.getMap(down.x, down.y) == 1 || maze.getMap(down.x, down.y) == 2 || maze.getMap(down.x, down.y) == 3)
            tsunami.push(new Vague(down, target, maze));


        //!\\Les cases vides autour -> ne pas rechercher en dehors du tableau
        //Si ça dépasse la taille, revenir au début (tp de gauche à droite et inversement, ainsi que haut bas)
        if(source.x < 0)
            source.x = maze.getWidth()-1;
        if(source.x > maze.getWidth())
            source.x = 0;
        if (source.y > maze.getHeight())
            source.y = 0;
        if (source.y < 0)
            source.y = maze.getHeight()-1;



        return false;
    }

    public Vague getFirstSon(){
        Vague son = this;
        while(father != null){
            son = father;
            father = father.getFather();
        }
        return son;
    }


}
