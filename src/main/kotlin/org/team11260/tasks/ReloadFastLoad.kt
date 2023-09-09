package org.team11260.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

/**
 * Uses ADB to send an intent to the fast-load library in app to preform a reload and waits for it to complete.
 */
abstract class ReloadFastLoad : DefaultTask() {

    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    @TaskAction
    fun execute() {
        project.exec {
            val output = ByteArrayOutputStream()
            it.standardOutput = output
            it.commandLine(
                getAdbExecutable().get(),
                "shell",
                "am",
                "broadcast",
                "-a",
                "team11260.RELOAD_FAST_LOAD",
            )
            println(output.toString(Charset.defaultCharset()))
        }
    }
}