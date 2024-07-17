import com.intellij.openapi.Disposable
import com.intellij.openapi.util.Disposer
import java.io.File
import java.nio.file.Path
import org.jetbrains.kotlin.analysis.api.projectStructure.KaSourceModule
import org.jetbrains.kotlin.analysis.api.standalone.buildStandaloneAnalysisAPISession
import org.jetbrains.kotlin.analysis.project.structure.builder.buildKtSdkModule
import org.jetbrains.kotlin.analysis.project.structure.builder.buildKtSourceModule
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms

private fun getJdkHomeFromSystemProperty(): File? {
  val javaHome = File(System.getProperty("java.home"))
  if (!javaHome.exists()) {
    logger.severe("Set existed java.home to use JDK")
    return null
  }
  return javaHome
}

internal fun createAnalysisSession(
  sourceSets: List<Path>,
  projectDisposable: Disposable = Disposer.newDisposable("StandaloneAnalysisAPISession.project"),
): KotlinAnalysis {
  var sourceModule: KaSourceModule? = null
  val analysisSession = buildStandaloneAnalysisAPISession(projectDisposable = projectDisposable) {
    buildKtModuleProvider {
      val jdkModule = getJdkHomeFromSystemProperty()?.let { jdkHome ->
        buildKtSdkModule {
          addBinaryRootsFromJdkHome(jdkHome.toPath(), isJre = true)
          libraryName = "JDK"
        }
      }
      platform = JvmPlatforms.defaultJvmPlatform
      sourceModule = buildKtSourceModule {
        // default is LanguageVersion.LATEST_STABLE.
        //  languageVersionSettings = getLanguageVersionSettings(sourceSet.languageVersion, sourceSet.apiVersion)
        platform = JvmPlatforms.defaultJvmPlatform
        moduleName = "<module-main>"
        addSourceRoots(sourceSets)
        jdkModule?.let { addRegularDependency(it) }
      }
      addModule(sourceModule!!)
    }
  }
  return KotlinAnalysis(sourceModule!!, analysisSession, projectDisposable)
}
