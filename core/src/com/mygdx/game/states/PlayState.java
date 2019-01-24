package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.sprites.Heli;

public class PlayState extends State {
    private Heli heli;
    private Texture bg;
    BitmapFont font = new BitmapFont();


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        heli = new Heli(50, 300);
        bg = new Texture("background.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            heli.jump();

    }

    @Override
    public void update(float dt) {
        handleInput();
        heli.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0,0, 480, 800);
        sb.draw(heli.getTexture(), heli.getPosition().x, heli.getPosition().y);
        font.draw(sb, heli.getPosition().toString(), 10, 780);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
