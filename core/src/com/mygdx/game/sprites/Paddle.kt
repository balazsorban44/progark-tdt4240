package com.mygdx.game.sprites

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.Game

class Paddle(height: Float, width: Float, posX: Float, posY: Float) {
    var size = 0f
    private var height = height
    private var width = width
    private var position = Vector2(posX,posY)
    private var bounds = Rectangle(position.x, position.y, width, height)

    private val speed = 3f
    private val ball = Texture("ball.png")

    fun update(dt: Float) {
        val maxHeight = Game.HEIGHT - bounds.height
        bounds = Rectangle(position.x, position.y, width, height)


        // Reached top
        if (position.y > maxHeight) {
            position.y = maxHeight
        }

        // Reached bottom
        if (position.y < 0) {
            position.y = 0f
        }
    }

    fun getPosition(): Vector2 { return position }

    fun getPaddle(): Texture { return ball }

    fun moveUp() { position.add(0f, speed) }

    fun moveDown() { position.add(0f, -speed) }

    fun collides(ball: Rectangle): Boolean { return ball.overlaps(bounds) }

}