package fr.univ_lorraine.PathFinding;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.World;

import java.util.Stack;

public class Inondation {
    World world;


    public int getDirection(Vector2 source, Vector2 target){
        Stack<Vague> tsunami = new Stack<Vague>();
        boolean[][] detruit = new boolean[world.getMaze().getWidth()][world.getMaze().getHeight()];//-> tout est à false

		GridPoint2 pos = new GridPoint2((int)source.x, (int)source.y);
		GridPoint2 cible = new GridPoint2((int)target.x, (int)target.y);

        tsunami.add(new Vague(pos, cible, world.getMaze()));

        boolean stop = false;
        Vague last;

        do{
            if(tsunami.size() == 0)
                throw new NullPointerException("Target not in same maze");
            last = tsunami.pop();
            if(!detruit[last.getPos().x][last.getPos().y]){
                stop = stop || last.inonder(tsunami);
                detruit[last.getPos().x][last.getPos().y] = true;
            }
        }while(!stop);

        int nextDir = 0;

		GridPoint2 tmp = last.getFirstSon().getPos();
        Vector2 nextPos = new Vector2(tmp.x, tmp.y);

        //Recherche de la direction,
        //On as la position actuelle (source)
        //Et la prochaine position (nextPos)
        if(nextPos.y > source.y)
            nextDir = 0;
        if(nextPos.x > source.x)
            nextDir = 1;
        


        return nextDir;
    }

}
