package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.HelicopterGame

class Helicopter(x: Float, y: Float) {

    val texture = Sprite(Texture("heli1.png"))
    val position = Vector2(x, y)


    private val velocity: Vector2
    private val speed = 2f

    init {
        velocity = Vector2(randomWithRange(-200, 220).toFloat(), randomWithRange(-200, 200).toFloat())
        if (velocity.x > 0) flipSpriteOnX() // Helicopter starts in the correct direction
    }

    private fun randomWithRange(min: Int, max: Int): Int {
        val result = (Math.random() * max - min + 1).toInt() + min
        return if (Math.abs(result) > 30) result else randomWithRange(min, max)
    }

    private fun flipSpriteOnX() {texture.flip(true, false)}

    fun update(dt: Float) {
        val maxHeight = HelicopterGame.HEIGHT - texture.height
        val maxWidth = HelicopterGame.WIDTH - texture.width

        velocity.scl(dt)


        // Reached top
        if (position.y > maxHeight) {
            position.y = maxHeight
            velocity.add(0f, velocity.y * -speed)
        }


        // Reached bottom
        if (position.y < 0) {
            position.y = 0f
            velocity.add(0f, velocity.y * -speed)
        }


        // Reached right
        if (position.x > maxWidth) {
            position.x = maxWidth
            velocity.add(velocity.x * -speed, 0f)
            flipSpriteOnX()
        }

        // Reached left
        if (position.x < 0) {
            position.x = 0f
            velocity.add(velocity.x * -speed, 0f)
            flipSpriteOnX()
        }

        //position.add(velocity.x, velocity.y)
        velocity.scl(1/dt)
    }

    fun move(origin: Vector2) {
        position.x = origin.x - texture.width / 2
        position.y = HelicopterGame.HEIGHT - origin.y - texture.height / 2
    }

    fun getPosition(): String {
        return """
              |x: ${position.x.toInt()}
              |y: ${position.y.toInt()}
              """.trimMargin()
    }

}
