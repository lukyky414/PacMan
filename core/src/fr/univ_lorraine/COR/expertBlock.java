package fr.univ_lorraine.COR;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Block;
import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.World;

public class expertBlock extends Expert {

	public expertBlock(Expert next){
		super(next);
	}



	@Override
	public boolean canBuild(int elementType) {
		return elementType == 0;
	}

	@Override
	public GameElement construct(World world, int x, int y) {
		return new Block(new Vector2(x,y), world);
	}
}
