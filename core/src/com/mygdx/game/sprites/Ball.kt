package com.mygdx.game.sprites

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Game

object Ball {

    private const val maxHeight = Game.HEIGHT.toFloat()
    private const val maxWidth = Game.WIDTH.toFloat()
    private const val INTERVAL: Int = 300
    private const val xSpeed = 150f

    val ball: Texture = Texture("ball.png")

    val position: Vector2 = Vector2(
        maxHeight / 2,
        maxWidth / 2
    )

    const val size: Float = Game.WIDTH / 100f


    private var randomYSpeed = (-INTERVAL..INTERVAL).shuffled().first().toFloat()

    private val velocity = Vector2(xSpeed, randomYSpeed)

    var bounds: Rectangle = Rectangle(position.x, position.y, size, size)

    var speed = -2f


    fun update(dt: Float) {

        bounds = Rectangle(position.x, position.y, size, size)

        velocity.scl(dt)
        position.add(velocity)

        // Reached top
        if (position.y > maxHeight - bounds.height) {
            position.y = maxHeight - bounds.height
            velocity.add(0f, velocity.y * speed)
        }

        // Reached bottom
        if (position.y < 0) {
            position.y = 0f
            velocity.add(0f, velocity.y * speed)
        }

        velocity.scl(1/dt)
    }


    fun hit(rightHit: Boolean) {

        Ball.speed -= 0.001f

        if(rightHit){
            position.x = maxWidth - 30f
            velocity.add(velocity.x * speed, 0f)
        }
        else {
            position.x = 30f
            velocity.add(velocity.x * speed, 0f)
        }
    }

    fun reset() {
        position.set(maxWidth / 2f, maxHeight / 2f)
        randomYSpeed = (-INTERVAL..INTERVAL).shuffled().first().toFloat()
        speed = -2f
    }


}



