package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.HelicopterGame
import com.mygdx.game.sprites.Helicopter

class PlayState(gsm: GameStateManager) : State(gsm) {
    private val helicopter: Helicopter = Helicopter(50f, 300f)
    private val bg: Texture = Texture("background.png")
    private var font = BitmapFont()


    override fun handleInput() {
        if (Gdx.input.justTouched()) helicopter.jump()
    }

    override fun update(dt: Float) {
        handleInput()
        helicopter.update(dt)
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(bg, 0f, 0f, HelicopterGame.WIDTH.toFloat(), HelicopterGame.HEIGHT.toFloat())
        sb.draw(helicopter.texture, helicopter.position.x, helicopter.position.y)
        font.draw(sb, helicopter.getPosition(),10f,780f)
        sb.end()
    }

    override fun dispose() {}
}
