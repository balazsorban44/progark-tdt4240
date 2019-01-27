package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.HelicopterGame

class Helicopter(x: Float, y: Float) {



    val sprite = Animation(
        1f/10f,
        Texture("heli4.png"),
        Texture("heli3.png"),
        Texture("heli2.png"),
        Texture("heli1.png")
    )

    private val width = sprite.keyFrames[0].width.toFloat()
    private val height = sprite.keyFrames[0].height.toFloat()

    val position = Vector2(x, y)

    private val velocity: Vector2
    private val speed = 2f
    private var moveFree = true
    private var bounds = Rectangle(
        position.x,
        position.y,
        width,
        height
    )

    init {
       velocity = Vector2(randomWithRange(-200, 220).toFloat(), randomWithRange(-200, 200).toFloat())
        if (velocity.x > 0) flipSpriteOnX() // Helicopter starts in the correct direction
    }

    private fun randomWithRange(min: Int, max: Int): Int {
        return (Math.random() * max - min + 1).toInt() + min
    }

    private fun flipSpriteOnX() {
        // NOTE: Must be fixed
        //sprite.flip(false, true)
    }

    fun update(dt: Float, obstacles: List<Helicopter>) {
        val maxHeight = HelicopterGame.HEIGHT - height
        val maxWidth = HelicopterGame.WIDTH - width
        bounds = Rectangle(position.x, position.y, width, height)

        velocity.scl(dt)

        obstacles.forEach {i -> run {
            val obstacleBounds = Rectangle(
                    i.position.x,
                    i.position.y,
                    i.width,
                    i.height
                )
            if (bounds.overlaps(obstacleBounds)) {
                velocity.add(velocity.x * -speed, velocity.y * -speed)
                flipSpriteOnX()
            }

        }}

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

        if (moveFree) position.add(velocity.x, velocity.y)

        velocity.scl(1/dt)
    }

    fun control(touch: Vector2) {
        if (bounds.contains(touch)) moveFree = false
        if (!moveFree) {
            position.x = touch.x - width / 2
            position.y = touch.y - height / 2
        }
    }

    fun setFree() {moveFree = true}

    fun getPosition(): String {
        return """
              |x: ${position.x.toInt()}
              |y: ${position.y.toInt()}
              """.trimMargin()
    }

}
