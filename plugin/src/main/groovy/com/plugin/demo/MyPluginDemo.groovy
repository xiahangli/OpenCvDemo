package com.plugin.demo
import org.gradle.api.Plugin
import org.gradle.api.Project
public class MyPluginDemo implements Plugin<Project> {

    @Override
    void apply(Project project) {
        //在apply这个插件的地方，运行build脚本，会输出下面这串字符
        println "夏杭礼的测试插件"

//        project.task('plugin-task') << {
//            println "hello, this is a opencv_test task!"
//        }
    }
}