package org.team11260.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction

abstract class DeployFastLoad : DefaultTask() {

    @InputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    @Input
    abstract fun getBundleBaseName(): Property<String>

    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    @Input
    abstract fun getDeployLocation(): Property<String>

    @TaskAction
    fun execute() {
        project.exec {
            it.commandLine(
                getAdbExecutable().get(),
                "push",
                getOutputDir().file("${getBundleBaseName().get()}.jar").get().asFile.absolutePath,
                getDeployLocation().get(),
            )
        }
    }
}