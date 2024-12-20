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
        maven {  url = uri( "https://devrepo.kakao.com/nexus/repository/kakaomap-releases/") }
        google()
        mavenCentral()
    }
}

rootProject.name = "Aecpple"
include(":app")
