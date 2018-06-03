package fr.univ_lorraine.model;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.PathFinding.Inondation;

import java.util.ArrayList;
import java.util.Random;

public class YellowGhost extends Ghost {


	public YellowGhost(Vector2 pos, World w){
		super(pos, w, 2f);
		setDirection(UP);
	}

	@Override
	void rechercheDir(int currX, int currY) {
		Pacman pac = this.world.getPacman();
		this.setDirection(shortPath.getDirection(new GridPoint2(currX, currY), new GridPoint2((int)pac.getPosition().x, (int)pac.getPosition().y)));
	}

	@Override
	void rechercheFuiteDit(int currX, int currY) {
		Vector2 tmp;
		float tmpDist;
		float distanceMax = 0;
		int max = Movable.NOTHING;

		for(int dir = 0; dir <4; dir++){
			tmp = getNextIntersection(currX, currY, dir);

			tmpDist = tmp.dst(this.world.getPacman().getPosition());
			if(tmpDist > distanceMax){
				max = dir;
				distanceMax = tmpDist;
			}

		}

		this.setDirection(max);
	}

	private Vector2 getNextIntersection(int baseX, int baseY, int direction){
		int nextX, nextY, type;
		int currX = baseX, currY = baseY;

		if(!tryDir(currX, currY, direction))
			return new Vector2(baseX, baseY);
		do {
			nextX = getNextX(currX, direction);
			nextY = getNextY(currY, direction);

			//Si on rencontre le pacman dans ce couloir, on fait demi tour, donc on retourne a la pos de depart
			if(nextX == (int) this.world.getPacman().getPosition().x
					&& nextY == (int) this.world.getPacman().getPosition().y)
				return new Vector2(baseX, baseY);

			type = this.world.getMaze().getMap(nextX, nextY);
			if (type == 0 || type == 3) {
				switch (direction) {
					case UP:
						if (tryDir(currX, currY, RIGHT))
							direction = RIGHT;
						else {
							if (tryDir(currX, currY, LEFT))
								direction = LEFT;
							else//demi tour, donc retourn a la pos de depart
								return new Vector2(currX, currY);
						}
						break;
					case RIGHT:
						if (tryDir(currX, currY, UP))
							direction = UP;
						else {
							if (tryDir(currX, currY, DOWN))
								direction = DOWN;
							else//demi tour, donc retourn a la pos de depart
								return new Vector2(currX, currY);
						}
						break;
					case DOWN:
						if (tryDir(currX, currY, RIGHT))
							direction = RIGHT;
						else {
							if (tryDir(currX, currY, LEFT))
								direction = LEFT;
							else//demi tour, donc retourn a la pos de depart
								return new Vector2(currX, currY);
						}
						break;
					case LEFT:
						if (tryDir(currX, currY, UP))
							direction = UP;
						else {
							if (tryDir(currX, currY, DOWN))
								direction = DOWN;
							else//demi tour, donc retourn a la pos de depart
								return new Vector2(currX, currY);
						}
						break;
				}
			}

			currX = getNextX(currX, direction);
			currY = getNextY(currY, direction);
		}while(type != 2);

		return new Vector2(currX, currY);
	}

}
