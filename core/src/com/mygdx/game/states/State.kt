package com.mygdx.game.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State protected constructor(protected var gsm: GameStateManager) {
    protected abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch, dt: Float)
    abstract fun dispose()
}
