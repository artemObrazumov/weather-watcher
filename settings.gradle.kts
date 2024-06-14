pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Weather watcher"
include(":app")
include(":common:ui")
include(":feature:main:domain")
include(":feature:main:data")
include(":feature:main:presentation")
include(":common:feature:weather:domain")
include(":common:feature:weather:data")
include(":keys")
include(":common:network")
include(":common:feature:city:data")
include(":common:feature:city:domain")
include(":common:database:city:room")
include(":common:navigation")
