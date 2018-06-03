package fr.univ_lorraine.PathFinding;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.model.World;

import java.util.LinkedList;
import java.util.Stack;

public class Inondation {
	World world;
	GameElement target;

	public Inondation(World world, GameElement target){
		this.world = world;
		this.target = target;
	}

    public int getDirection(GridPoint2 source){
        LinkedList<Vague> tsunami = new LinkedList<Vague>();
        boolean[][] detruit = new boolean[world.getMaze().getWidth()][world.getMaze().getHeight()];//-> tout est à false

		GridPoint2 cible = new GridPoint2((int)target.getPosition().x, (int)target.getPosition().y);

        tsunami.add(new Vague(source, cible, world.getMaze()));

        boolean stop = false;
        Vague last;

        do{

            last = tsunami.pollFirst();

			if(last == null)
				throw new NullPointerException("Target not in same maze");

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
            nextDir = Movable.UP;
        if(nextPos.x > source.x)
            nextDir = Movable.RIGHT;
        if(nextPos.y < source.y)
            nextDir = Movable.DOWN;
        if(nextPos.x < source.x)
            nextDir = Movable.LEFT;
        


        return nextDir;
    }

}
