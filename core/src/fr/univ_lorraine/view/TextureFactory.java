package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;
import fr.univ_lorraine.model.*;

import java.util.HashMap;

public class TextureFactory {
    static private TextureFactory instance = null;

    private HashMap<Class<?>, iTexturable> textures;

    private TextureFactory(World world){
    	textures = new HashMap<Class<?>, iTexturable>();
    	textures.put(
    			Block.class,
				new TextureUnique("images/bloc.png")
		);
		textures.put(
				Pellet.class,
				new TextureUnique("images/pellet.png")
		);
		textures.put(
				SuperPellet.class,
				new TextureSuperPellet(.7f)
		);

		//Pacman
    	textures.put(
    			Pacman.class,
				new TexturePacman(world.getPacman(), .3f)
		);

    	//redGhost
    	textures.put(
    			RedGhost.class,
				new TextureRedGhost(world.getRedGhost())
		);
		//pinkGhost
		textures.put(
				PinkGhost.class,
				new TexturePinkGhost(world.getPinkGhost())
		);
		//blueGhost
		textures.put(
				BlueGhost.class,
				new TextureBlueGhost(world.getBlueGhost())
		);
		//yellowGhost
		textures.put(
				YellowGhost.class,
				new TextureYellowGhost(world.getYellowGhost())
		);
    }


	public Texture getTexture(Class<?> c, float delta){
		return textures.get(c).getTexture(delta);
	}

    static public void reset(World world){
        instance = new TextureFactory(world);
    }

    static public TextureFactory getInstance(){
    	return instance;
    }
}
