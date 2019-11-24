package com.xia.plugin

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TaskListener implements TaskExecutionListener, BuildListener{

    private static final String    TAG = "[Xiahangli] "

    @Override
    void buildStarted(Gradle gradle) {
        println(TAG + "build started.")
    }

    @Override
    void settingsEvaluated(Settings settings) {
        println(TAG + "setting evaluated.")
    }

    @Override
    void projectsLoaded(Gradle gradle) {
        println(TAG + "project loaded.")
    }

    @Override
    void projectsEvaluated(Gradle gradle) {
        println(TAG + "project evaluated.")
    }

    @Override
    void buildFinished(BuildResult buildResult) {
        //项目构建完成回调这个方法
        println(TAG+"build finish")
    }

    @Override
    void beforeExecute(Task task) {
        println(TAG+"task before : "+task.getName())
    }

    @Override
    void afterExecute(Task task, TaskState taskState) {
        println(TAG + "task after : " + task.getName())
        if (task.getName().equals("packageRelease")){
            //若为打包，做自己的事
        }
    }
}