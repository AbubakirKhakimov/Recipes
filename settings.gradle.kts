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
        maven ( url = "https://jitpack.io" )
    }
}

rootProject.name = "Recipes"
include(":app")
include(":provider:data")
include(":provider:local")
include(":provider:network")
include(":core:common")
include(":core:presentation")
include(":core:presentation-impl")
include(":core:security")
include(":core:common-impl")
include(":core:resource")
include(":wiring")
include(":features:home")
