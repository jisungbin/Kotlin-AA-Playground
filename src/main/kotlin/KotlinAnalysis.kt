import com.intellij.openapi.Disposable
import com.intellij.openapi.util.Disposer
import java.io.Closeable
import org.jetbrains.kotlin.analysis.api.projectStructure.KaSourceModule
import org.jetbrains.kotlin.analysis.api.standalone.StandaloneAnalysisAPISession

internal class KotlinAnalysis(
  val sourceModule: KaSourceModule,
  private val analysisSession: StandaloneAnalysisAPISession,
  private val projectDisposable: Disposable,
) : Closeable {
  val modulesWithFiles get() = analysisSession.modulesWithFiles

  override fun close() {
    Disposer.dispose(projectDisposable)
  }
}
