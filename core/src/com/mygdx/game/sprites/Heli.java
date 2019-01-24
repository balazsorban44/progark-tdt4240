package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Heli {
    private static final int GRAVITY = -15;

    private Vector3 position;
    private Vector3 velocity;
    private Texture heli;
    private Sprite sprite;

    public Heli(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(randomWithRange(-200, 220),randomWithRange(-200,200),0);
        heli = new Texture("heli1.png");
        sprite = new Sprite(heli);
    }

    int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() *range) + min;
    }

    boolean headingLeft(){
        return velocity.x > 0;
    }

    public void update(float dt){
        if(!headingLeft()){
            //sprite.flip(true,false);
        }
        if(position.y > 0)
            // Kommenter inn for gravity
            //velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(velocity.x,velocity.y, 0);


        if(position.y > 800 - sprite.getHeight())
            velocity.add(0, velocity.y * -2, 0);

        if(position.y < 10)
            velocity.add(0, velocity.y * -2, 0);

        if(position.x > 480 - sprite.getWidth())
            velocity.add(velocity.x * -2, 0,0);

        if(position.x < 0)
            velocity.add(velocity.x * -2,0,0);

        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Sprite getTexture() {
        return sprite;
    }

    public void jump(){
        velocity.y = 250;
    }
}
