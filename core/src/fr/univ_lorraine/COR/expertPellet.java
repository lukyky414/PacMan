package fr.univ_lorraine.COR;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.Pellet;
import fr.univ_lorraine.model.World;

public class expertPellet extends Expert {

	public expertPellet(Expert next){
		super(next);
	}



	@Override
	public boolean canBuild(int elementType) {
		return elementType == 1 || elementType == 2;
	}

	@Override
	public GameElement construct(World world, int x, int y) {
		return new Pellet(new Vector2(x,y), world);
	}
}
