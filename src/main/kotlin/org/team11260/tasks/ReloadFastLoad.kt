package org.team11260.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction

/**
 * Uses ADB to send an intent to the fast-load library in app to preform a reload and waits for it to complete.
 */
abstract class ReloadFastLoad : DefaultTask() {

    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    @TaskAction
    fun execute() {
        val result = project.exec {
            it.commandLine(
                getAdbExecutable().get(),
                "shell",
                "am",
                "broadcast",
                "-a",
                "team11260.RELOAD_FAST_LOAD",
            )
        }
        println("reload exit value: ${result.exitValue}")
    }
}