package com.plugin.demo
import org.gradle.api.Plugin
import org.gradle.api.Project
public class MyPluginDemo implements Plugin<Project> {

    @Override
    void apply(Project project) {

        println "hello, this is test plugin!"

        project.task('plugin-task') << {
            println "hello, this is a test task!"
        }
    }
}