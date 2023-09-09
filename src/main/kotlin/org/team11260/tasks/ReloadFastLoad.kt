package org.team11260.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.process.internal.ExecException
import java.io.ByteArrayOutputStream
import java.net.ConnectException
import java.nio.charset.Charset

/**
 * Uses ADB to send an intent to the fast-load library in app to preform a reload and waits for it to complete.
 */
abstract class ReloadFastLoad : DefaultTask() {

    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    @TaskAction
    fun execute() {
        try {
            val output = ByteArrayOutputStream()
            project.exec {
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
            if(!output.toString(Charset.defaultCharset()).contains("result=1")) {
                error("Failed to complete fast load reload, ensure FtcRobotController app is running and has fast-load dependency installed.")
            }
        } catch (e: ExecException) {
            error("Failed to connect to robot, ensure ADB connected to robot.")
        }
    }
}