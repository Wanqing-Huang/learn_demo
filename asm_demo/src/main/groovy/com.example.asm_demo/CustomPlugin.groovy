package com.example.asm_demo

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        LogUtil logger = new LogUtil()
        logger.warn('[CustomPlugin] apply plugin. project = ' + project.name)

        AppExtension appExtension = project.extensions.getByType(AppExtension)
        appExtension.registerTransform(new CustomTransform())
    }
}