package fr.univ_lorraine.view;

import com.badlogic.gdx.graphics.Texture;
import fr.univ_lorraine.model.RedGhost;
import fr.univ_lorraine.model.Movable;



public class TextureRedGhost implements iTexturable {

    private RedGhost red;
    private float deltaT = 0.0f, seuil;
    private int step = 0;

    private Texture[] textures;


    public TextureRedGhost(RedGhost redG, float seuil){
        this.seuil = seuil;
        this.red = redG;
        textures = new Texture[]{
                new Texture("images/ghost1.png"),
               new Texture("images/ghostDead.png"),
               new Texture("images/ghostEscaping.png")
        };

    }

   public Texture getTexture(float delta){
        if(red.getDead())
            return textures[1];
       else if(red.getEscaping())
            return textures[2];
        else
            return textures[0];

    }

}
