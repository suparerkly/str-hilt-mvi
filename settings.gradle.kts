pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://www.jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
        maven {
            url = uri("https://gitlab.com/api/v4/projects/59221580/packages/maven")
            name = "splLibApplication"
            credentials(HttpHeaderCredentials::class) {
                name = "Private-Token"
                value = "glpat-cFLik9u1WmyN-9oPjia7"
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }

}

rootProject.name = "SprApplication"
include(":app")
include(":features:common")
include(":core:firebase")
include(":data")
