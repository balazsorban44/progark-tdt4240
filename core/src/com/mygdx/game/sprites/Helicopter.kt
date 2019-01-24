package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.HelicopterGame

class Helicopter(x: Float, y: Float) {

    val position: Vector2 = Vector2(x, y)
    private val velocity: Vector2

    val texture: Sprite
    private val speed: Float = 2f

    init {
        velocity = Vector2(randomWithRange(-200, 220).toFloat(), randomWithRange(-200, 200).toFloat())
        texture = Sprite(Texture("heli1.png"))

        if (velocity.x > 0) flipSpriteOnX() // Helicopter starts in the correct direction
    }

    private fun randomWithRange(min: Int, max: Int): Int {
        val result = (Math.random() * max - min + 1).toInt() + min
        return if (Math.abs(result) > 30) result else randomWithRange(min, max)
    }

    private fun flipSpriteOnX() {texture.flip(true, false)}

    fun update(dt: Float) {
        val maxHeight = HelicopterGame.HEIGHT - texture.height
        val maxWidth= HelicopterGame.WIDTH - texture.width

        if (position.y > 0)
            //Kommenter inn for gravity
            //velocity.add(0, GRAVITY, 0);

        velocity.scl(dt)
        position.add(velocity.x, velocity.y)


        // Reached top
        if (position.y > maxHeight) velocity.add(0f, velocity.y * -speed)

        if (position.y > maxHeight) position.y = maxHeight // Ensures that helicopter don't fly above the window

        // Reached bottom
        // REVIEW: Why not 0?
        if (position.y < 10) velocity.add(0f, velocity.y * -speed)


        // Reached right
        if (position.x > maxWidth) {
            velocity.add(velocity.x * -speed, 0f)
            flipSpriteOnX()
        }

        // Reached left
        if (position.x < 0) {
            velocity.add(velocity.x * -speed, 0f)
            flipSpriteOnX()
        }

        velocity.scl(1 / dt)
    }

    fun jump() {velocity.y = 250f}

    fun getPosition(): String {
        return """
              |x: ${position.x.toInt()}
              |y: ${position.y.toInt()}
              """.trimMargin()
    }

    companion object {private val GRAVITY = -15 }
}
