package org.team11260.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import javax.inject.Inject

/**
 * Uses ADB to send an intent to the fast-load library in app to preform a reload and waits for it to complete.
 */
abstract class ReloadFastLoad : DefaultTask() {

    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    @Inject
    abstract fun getExecOperations(): ExecOperations

    @TaskAction
    fun execute() {
        val output = ByteArrayOutputStream()
        val result = getExecOperations().exec {
            it.isIgnoreExitValue = true
            it.standardOutput = output
            it.commandLine(
                getAdbExecutable().get(),
                "shell",
                "am",
                "broadcast",
                "-a",
                "team11260.RELOAD_FAST_LOAD",
            )
        }
        if (result.exitValue != 0) {
            error("Failed to connect to robot, ensure ADB connected to robot.")
        }
        if(!output.toString(Charset.defaultCharset()).contains("result=1")) {
            error("Failed to complete fast load reload, ensure FtcRobotController app is running and has fast-load dependency installed.")
        }
    }
}
