package fr.univ_lorraine.model;

import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.model.World;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Ghost;

import java.util.Random;

public class RedGhost extends Ghost {

    Random rand = new Random(System.currentTimeMillis());


    public RedGhost(Vector2 pos, World w){
        super(pos, w);
        setDirection(UP);
    }

	@Override
	int rechercheDir(int currX, int currY) {
		return rand.nextInt(3) + 0;
	}
}
