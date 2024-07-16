plugins {
  kotlin("jvm") version "2.0.0"
}

repositories {
  mavenCentral()
  gradlePluginPortal()

  maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide") {
    name = "KotlinIde-JBCache"
  }
  maven("https://cache-redirector.jetbrains.com/maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide-plugin-dependencies") {
    name = "KotlinIdePluginDependencies-JBCache"
  }

  maven("https://cache-redirector.jetbrains.com/intellij-repository/releases") {
    name = "IjRepository-JBCache"
  }
  maven("https://cache-redirector.jetbrains.com/intellij-third-party-dependencies") {
    name = "IjThirdParty-JBCache"
  }

  maven("https://cache-redirector.jetbrains.com/repo.maven.apache.org/maven2") {
    name = "MavenCentral-JBCache"
  }
  maven("https://cache-redirector.jetbrains.com/dl.google.com.android.maven2") {
    name = "Google-JBCache"
  }
}

dependencies {
  listOf(
    "org.jetbrains.kotlin:analysis-api-standalone-for-ide",
    "org.jetbrains.kotlin:high-level-api-impl-base-for-ide",
    "org.jetbrains.kotlin:high-level-api-fir-for-ide",
    "org.jetbrains.kotlin:analysis-api-platform-interface-for-ide",
    "org.jetbrains.kotlin:symbol-light-classes-for-ide",
  ).forEach { dep ->
    implementation("$dep:2.0.0-dev-8570") {
      // FIXME KTIJ-19820
      isTransitive = false
    }
  }
}
