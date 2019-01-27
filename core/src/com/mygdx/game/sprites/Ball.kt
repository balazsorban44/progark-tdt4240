package com.mygdx.game.sprites

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.HelicopterGame

class Ball(size: Float, x: Float, y: Float) {
    var size = size
    private var position = Vector2(x,y)
    private val INTERVAL: Int = 300
    private var xSpeed = 150f
    private var randomYSpeed = (-INTERVAL..INTERVAL).shuffled().first().toFloat()
    private var velocity = Vector2(xSpeed, randomYSpeed)
    private var bounds = Rectangle(position.x, position.y, size, size)

    private val speed = 2f
    private val ball = Texture("ball.png")
    val maxHeight = HelicopterGame.HEIGHT.toFloat()
    val maxWidth = HelicopterGame.WIDTH.toFloat()
    fun update(dt: Float) {

        bounds = Rectangle(position.x, position.y, size, size)

        velocity.scl(dt)
        position.add(velocity)

        // Reached top
        if (position.y > maxHeight - bounds.height) {
            position.y = maxHeight - bounds.height
            velocity.add(0f, velocity.y * -speed)
        }

        // Reached bottom
        if (position.y < 0) {
            position.y = 0f
            velocity.add(0f, velocity.y * -speed)
        }

        velocity.scl(1/dt)
    }

    fun getPosition(): Vector2 {
        return position
    }

    fun getBall(): Texture {
        return ball
    }

    fun getBounds(): Rectangle {
        return bounds
    }

    fun hit(rightHit: Boolean) {
        if(rightHit){
            position.x = maxWidth - 30f
            velocity.add(velocity.x * -speed, 0f)
        }
        else {
            position.x = 30f
            velocity.add(velocity.x * -speed, 0f)
        }


    }

    fun reset() {
        position.set(maxWidth / 2, maxHeight / 2)
        randomYSpeed = (-INTERVAL..INTERVAL).shuffled().first().toFloat()
    }
}



