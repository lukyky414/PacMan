package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Maze

import java.util.Stack;

public class Inondation {
    World world;


    public int getDirection(Vector2 source, Vector2 cible){
        Stack<Vague> tsunami = new Stack<Vague>();
        boolean[][] detruit = new boolean[world.getMaze().getWidth()][world.getMaze().getHeight()]();//-> tout est à false

        tsunami.add(new Vague(source, cible));

        boolean stop;
        Vague last;

        do{
            if(tsunami.size() == 0)
                throw new Exception("Target not in same maze");
            last = tsunami.pop();
            if(!detruit[last.getPos().x][last.getPos().y]){
                stop = stop || last.inonder(tsunami);
                detruit[last.getPos().x][last.getPos().y] = true;
            }
        }while(!stop);

        int nextDir;

        Vector2 nextPos = last.getFirstSon().getPos();

        //Recherche de la direction,
        //On as la position actuelle (source)
        //Et la prochaine position (nextPos)

        return nextDir;
    }

}
