package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.HelicopterGame
import com.mygdx.game.sprites.Helicopter

class PlayState(gsm: GameStateManager) : State(gsm) {
    private val helicopter: Helicopter = Helicopter(50f, 300f)
    private val helicopter2: Helicopter = Helicopter(10f, 500f)
    private val helicopter3: Helicopter = Helicopter(200f, 40f)
    private val bg: Texture = Texture("background.png")
    private var font = BitmapFont()
    private var elapsedTime = 0f

    override fun handleInput() {
        if (Gdx.input.isTouched) {
            val touch = Vector2(Gdx.input.x.toFloat(), HelicopterGame.HEIGHT - Gdx.input.y.toFloat())
            helicopter.control(touch)
            helicopter2.control(touch)
            helicopter3.control(touch)
        } else {
            helicopter.setFree()
            helicopter2.setFree()
            helicopter3.setFree()
        }
    }

    override fun update(dt: Float) {
        handleInput()
        helicopter.update(dt, listOf(helicopter2, helicopter3))
        helicopter2.update(dt, listOf(helicopter, helicopter3))
        helicopter3.update(dt, listOf(helicopter, helicopter2))
    }


    override fun render(sb: SpriteBatch, dt: Float) {
        elapsedTime += dt
        sb.begin()
        sb.draw(bg, 0f, 0f, HelicopterGame.WIDTH.toFloat(), HelicopterGame.HEIGHT.toFloat())
        sb.draw(
            helicopter.sprite.getKeyFrame(elapsedTime, true),
            helicopter.position.x,
            helicopter.position.y
        )
        sb.draw(
            helicopter2.sprite.getKeyFrame(elapsedTime, true),
            helicopter2.position.x,
            helicopter2.position.y
        )
        sb.draw(
            helicopter3.sprite.getKeyFrame(elapsedTime, true),
            helicopter3.position.x,
            helicopter3.position.y
        )
        font.draw(sb, helicopter.getPosition(),10f,780f) // Task 2b it draws the first helicopter's coordinates
        sb.end()
    }

    override fun dispose() {}
}
