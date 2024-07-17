import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

val logger = Logger.getLogger("main")!!.apply {
  level = Level.ALL
}

fun main() {
  logger.info("Hello World!")
  val analysisSession = createAnalysisSession(
    sourceSets = listOf(File("Users/jisungbin/IdeaProjects/kotlin-aa-playground/src/main/kotlin/test_space").toPath()),
  )
  logger.log(Level.INFO, "AnalysisSession: {0}", analysisSession)
}
