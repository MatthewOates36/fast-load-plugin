package org.team11260.tasks

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.jvm.tasks.Jar

/**
 * Compiles a jar from the dex files produced by the Android task mergeExtDexDebug.
 */
abstract class DexFastLoad : Jar() {

    @OutputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    @Input
    abstract fun getDexBaseName(): Property<String>

    init {
        this.dependsOn("dexBuilderDebug")
        this.destinationDirectory.set(this.getOutputDir())
        this.archiveBaseName.set(this.getDexBaseName())
        this.from(project.buildDir.resolve("intermediates/project_dex_archive/debug/dexBuilderDebug/out")) {
            it.exclude("*.jar")
        }
    }
}