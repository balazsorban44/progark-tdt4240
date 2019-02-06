package com.mygdx.game.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch

import java.util.Stack

class GameStateManager {

    private val states: Stack<State> = Stack()

    fun push(state: State) {states.push(state)}

    fun set(state: State) {
        pop()
        push(state)
    }

    fun update(dt: Float) {states.peek().update(dt)}

    fun render(sb: SpriteBatch, dt: Float) {states.peek().render(sb, dt)}

    private fun pop() { states.pop() }
}
