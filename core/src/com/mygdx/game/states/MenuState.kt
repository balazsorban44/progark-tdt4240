package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.Game

class MenuState(gsm: GameStateManager) : State(gsm) {
    private val background: Texture = Texture("background.png")
    private var font = BitmapFont()



    public override fun handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.H))
            gsm.set(HelicopterState(gsm))

        else if(Gdx.input.isKeyPressed(Input.Keys.P))
            gsm.set(PongState(gsm))

        dispose()
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch, dt: Float) {
        sb.begin()
        sb.draw(background, 0f, 0f, Game.WIDTH.toFloat(), Game.HEIGHT.toFloat())
        font.draw(sb, "Press P to play Pong, H to play Helicopter game",130f,45f)

        sb.end()
    }

    override fun dispose() {
        background.dispose()
    }
}
