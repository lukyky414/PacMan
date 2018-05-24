package fr.univ_lorraine.PathFinding;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
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

        //!\\Les cases vides autour -> ne pas rechercher en dehors du tableau
        //Si ça dépasse la taille, revenir au début (tp de gauche à droite et inversement, ainsi que haut bas)

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
