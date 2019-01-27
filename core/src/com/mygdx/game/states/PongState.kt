package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.HelicopterGame
import com.mygdx.game.sprites.Ball
import com.mygdx.game.sprites.Helicopter
import com.mygdx.game.sprites.Paddle

class PongState(gsm: GameStateManager) : State(gsm) {
    private var font = BitmapFont()
    private var padding = 20f
    private var maxHeight = HelicopterGame.HEIGHT.toFloat()
    private var maxWidth = HelicopterGame.WIDTH.toFloat()

    // Ball
    private val ballSize = maxWidth / 100.toFloat()
    private var ball = Ball(ballSize, maxWidth / 2, maxHeight / 2)


    private var paddleHeight = 100f
    private var paddleWidth = 5f

    // Right paddle
    private var rScore = 0
    private var rPosX = maxWidth - padding
    private var rPosY = (maxHeight / 2) - (paddleHeight / 2)
    private var paddleR = Paddle(paddleHeight, paddleWidth, rPosX, rPosY )

    // Left paddle
    private var lScore = 0
    private var lPosX = 0 + padding
    private var lPosY = (maxHeight / 2) - (paddleHeight / 2)
    private var paddleL = Paddle(paddleHeight, paddleWidth, lPosX, lPosY )



    override fun handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) paddleR.moveUp()
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) paddleR.moveDown()

        if(Gdx.input.isKeyPressed(Input.Keys.W)) paddleL.moveUp()
        if(Gdx.input.isKeyPressed(Input.Keys.S)) paddleL.moveDown()


    }

    override fun update(dt: Float) {
        handleInput()
        ball.update(dt)
        paddleR.update(dt)
        paddleL.update(dt)

        // Reached left
        if (ball.getPosition().x < 0) {
            ball.reset()
            rScore ++
        }

        // Reached right
        if (ball.getPosition().x > maxWidth) {
            ball.reset()
            lScore ++
        }

        if(paddleR.collides(ball.getBounds())) {
            ball.hit(true)
        }
        if(paddleL.collides(ball.getBounds())) ball.hit(false)
    }


    override fun render(sb: SpriteBatch, dt: Float) {
        var posBall = ball.getPosition()
        var posPaddleR = paddleR.getPosition()
        var posPaddleL = paddleL.getPosition()

        sb.begin()
        sb.draw(ball.getBall(), posBall.x, posBall.y, ballSize, ballSize)
        sb.draw(paddleR.getPaddle(), posPaddleR.x, posPaddleR.y, paddleWidth, paddleHeight)
        sb.draw(paddleL.getPaddle(), posPaddleL.x, posPaddleL.y, paddleWidth, paddleHeight)
        font.draw(sb, getScores(),maxWidth / 2 - padding, maxHeight - padding)
        sb.end()

    }

    override fun dispose() {}

    fun getScores(): String {
        return "${lScore} : ${rScore}"
    }
}

