package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.states.GameStateManager
import com.mygdx.game.states.MenuState

class HelicopterGame : ApplicationAdapter() {

    private var gsm: GameStateManager? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        gsm!!.push(MenuState(gsm!!))
    }

    override fun render() {
        val dt = Gdx.graphics.deltaTime
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm!!.update(dt)
        gsm!!.render(batch!!, dt)
    }

    override fun dispose() {batch!!.dispose()}


    companion object {
        const val WIDTH = 800
        const val HEIGHT = 480
        const val TITLE = "Helicopter"
    }
}
