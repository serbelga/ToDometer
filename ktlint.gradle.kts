import org.codehaus.groovy.runtime.ProcessGroovyMethods.execute
import org.codehaus.groovy.runtime.ProcessGroovyMethods.waitForProcessOutput
import java.io.ByteArrayOutputStream

val ktlint: Configuration by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.43.0") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}

val ktlintGit by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Run ktlint on changed files."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"

    doFirst {
        val gitDiffFilesList = getGitDiffFilesList()

        println("List: $gitDiffFilesList")
        args = gitDiffFilesList
    }
}

fun getGitDiffFilesList(): List<String> {
    print("Start")
    val cmd = "git diff --name-only --cached --relative | grep '\\.kt\\?\$'"
    val process = execute(arrayOf("sh", "-c", cmd))
    val outputStream = ByteArrayOutputStream()
    waitForProcessOutput(process, outputStream, System.err)
    println(outputStream.toString())
    return outputStream.toString().split("\\.kt\n")
}
