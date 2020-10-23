package com.example.asm_demo

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

import java.util.logging.Level
import java.util.logging.Logger

class CustomPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        LogUtil.warn("============== CustomPlugin applied ==============")
        AppExtension appExtension = project.extensions.getByType(AppExtension)
        //注册Transform
        appExtension.registerTransform(new CustomTransform())
    }
}