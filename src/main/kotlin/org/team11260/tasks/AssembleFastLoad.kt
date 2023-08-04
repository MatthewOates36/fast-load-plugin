package org.team11260.tasks

import com.android.tools.r8.CompilationMode
import com.android.tools.r8.D8
import com.android.tools.r8.D8Command
import com.android.tools.r8.OutputMode
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Assembles the merged dex jar file from the individual dex jar.
 */
abstract class AssembleFastLoad : DefaultTask() {

    @InputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    @Input
    abstract fun getDexBaseName(): Property<String>

    @Input
    abstract fun getBundleBaseName(): Property<String>

    @TaskAction
    fun execute() {
        // Make sure no existing jar file exists as this will cause d8 to fail
        val inputFile = getOutputDir().file("${getDexBaseName().get()}.jar").get().asFile
        val outputFile = getOutputDir().file("${getBundleBaseName().get()}.jar").get().asFile
        outputFile.delete()

        D8.run(D8Command.builder().addProgramFiles(inputFile.toPath()).setMode(CompilationMode.DEBUG).setOutput(outputFile.toPath(), OutputMode.DexIndexed).build())
    }
}