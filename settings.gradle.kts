pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url =  uri("https://maven.google.com") } // For Gradle < 4.0
    }
}

include(":app")
include(":monthcalendar")
