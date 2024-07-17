plugins {
  kotlin("jvm") version "2.0.0"
}

repositories {
  mavenCentral()
  gradlePluginPortal()
  maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-ide-plugin-dependencies/")
}

dependencies {
  implementation("org.jetbrains.kotlin:analysis-api-standalone-for-ide:2.0.0-release-341")
}
