plugins {
  kotlin("jvm") version "2.0.0"
}

repositories {
  mavenCentral()
  maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
  maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide-plugin-dependencies")
  maven(url = "https://www.jetbrains.com/intellij-repository/releases")
  maven(url = "https://packages.jetbrains.team/maven/p/ij/intellij-dependencies/")
}

dependencies {
  implementation(libs.intellij.java.psi.api) { isTransitive = false }

  listOf(
    libs.kotlin.high.level.api.api,
    libs.kotlin.analysis.api.standalone,
  ).forEach {
    implementation(it) {
      isTransitive = false // see KTIJ-19820
    }
  }
  listOf(
    libs.kotlin.high.level.api.impl,
    libs.kotlin.high.level.api.fir,
    libs.kotlin.low.level.api.fir,
    libs.kotlin.analysis.api.platform,
    libs.kotlin.symbol.light.classes,
  ).forEach {
    implementation(it) {
      isTransitive = false // see KTIJ-19820
    }
  }
  // copy-pasted from Analysis API https://github.com/JetBrains/kotlin/blob/a10042f9099e20a656dec3ecf1665eea340a3633/analysis/low-level-api-fir/build.gradle.kts#L37
  implementation("com.github.ben-manes.caffeine:caffeine:2.9.3")

  implementation(libs.kotlinx.collections.immutable)
  implementation(libs.kotlin.compiler.k2) {
    isTransitive = false
  }

  // TODO [beresnev] get rid of it
  implementation(libs.kotlinx.coroutines.core)
}
