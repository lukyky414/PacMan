package fr.univ_lorraine.COR;

import fr.univ_lorraine.model.GameElement;
import fr.univ_lorraine.model.World;

public abstract class Expert {
	public Expert next;

	public Expert(Expert next){
		this.next = next;
	}

	public abstract boolean canBuild(int elementType);
	public abstract GameElement construct(World world, int x, int y);


	public GameElement build(World world, int elementType, int x, int y){
		if(canBuild(elementType))
			return construct(world, x, y);

		if(next == null)
			return null;

		return next.build(world, elementType, x, y);
	}
}
