package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.HelicopterGame

class MenuState(gsm: GameStateManager) : State(gsm) {
    private val background: Texture = Texture("background.png")
    private var font = BitmapFont()



    public override fun handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(PlayState(gsm))
            dispose()
        }
        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            gsm.set(PongState(gsm))
            dispose()
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch, dt: Float) {
        sb.begin()
        sb.draw(background, 0f, 0f, HelicopterGame.WIDTH.toFloat(), HelicopterGame.HEIGHT.toFloat())
        font.draw(sb, "Press P to play Pong",130f,45f)

        sb.end()
    }

    override fun dispose() {
        background.dispose()
    }
}
