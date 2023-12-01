pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidLab"
include(":app")
include(":ch3-view")
include(":ch4-layout")
include(":ch5-event")
include(":ch6-resource")
include(":ch7-database")
include(":ch7-file")
include(":ch7-settings")
include(":ch8-menu")
include(":ch9-material")
include(":ch11-receiver")
include(":ch12-service")
include(":ch13-provider")
include(":ch15-retrofit")
