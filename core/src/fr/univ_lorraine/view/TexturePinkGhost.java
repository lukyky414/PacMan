package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;
import com.sun.tools.javac.main.Option;
import fr.univ_lorraine.model.PinkGhost;

public class TexturePinkGhost implements iTexturable{


    private PinkGhost pink;
    private float deltaT = 0.0f, seuil;
    private int step = 0;

    private Texture[] textures;


    public TexturePinkGhost(PinkGhost pinkG, float seuil){
        this.seuil = seuil;
        this.pink = pinkG;
        textures = new Texture[]{
                new Texture("images/ghost2.png"),
                new Texture("images/ghostDead.png"),
                new Texture("images/ghostEscaping.png")
        };

    }

    public Texture getTexture(float delta) {
	   /*if (red.getDead())
		   return textures[1];
	   else if (red.getEscaping())
		   return textures[2];
	   else
		   return textures[0];//*/
        return textures[pink.getEtat()];
    }
}
