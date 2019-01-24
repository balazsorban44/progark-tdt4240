package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.game.HelicopterGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {

        val config = LwjglApplicationConfiguration()

        config.width = HelicopterGame.WIDTH
        config.height = HelicopterGame.HEIGHT
        config.title = HelicopterGame.TITLE

        LwjglApplication(HelicopterGame(), config)

    }
}
