package org.team11260

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile

/**
 * Holds global properties for the plugin
 */
abstract class FastLoadExtension {

    /**
     * Location of ADB executable, uses configured build tools version by default.
     */
    @InputFile
    abstract fun getAdbExecutable(): RegularFileProperty

    /**
     * Where the bundles will be output.
     */
    @InputDirectory
    abstract fun getOutputDir(): DirectoryProperty

    /**
     * Name of the jar containing the individual dex files.
     */
    @Input
    abstract fun getDexBaseName(): Property<String>

    /**
     * Name of the jar contain the merged dex file.
     */
    @Input
    abstract fun getBundleBaseName(): Property<String>

    /**
     * Location in the Robot Controller storage the fast load jar should be placed.
     */
    @Input
    abstract fun getDeployLocation(): Property<String>
}