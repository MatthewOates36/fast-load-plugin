package org.team11260

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile


abstract class FastLoadExtension {

    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    @InputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    @Input
    abstract fun getDexBaseName(): Property<String>

    @Input
    abstract fun getBundleBaseName(): Property<String>

    @Input
    abstract fun getDeployLocation(): Property<String>
}