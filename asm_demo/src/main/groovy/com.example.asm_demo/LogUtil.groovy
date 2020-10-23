package com.example.asm_demo

import java.util.logging.Level
import java.util.logging.Logger

/**
 * @authot Quincy Jiang
 * Create at 2020.05.20
 * Log 工具
 */
class LogUtil {

    static info(String msg) {
        Logger.global.log(Level.INFO, "[CUSTOM PLUGIN] " + msg)
    }
    static warn(String msg) {
        Logger.global.log(Level.WARNING, "[CUSTOM PLUGIN] " + msg)
    }

    static error(String msg) {
        Logger.global.log(Level.SEVERE, "[CUSTOM PLUGIN] " + msg)
    }
}

