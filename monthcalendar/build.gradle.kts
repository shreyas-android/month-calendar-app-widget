@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.androidai.widget.monthcalendar"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        /*release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }*/
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }


    publishing {
        singleVariant("release") {
            // if you don't want sources/javadoc, remove these lines
            withSourcesJar()
            withJavadocJar()
        }
    }

    publishing {
       /* publications {
            create<MavenPublication>("release") {
                groupId = "com.androidai.widget"
                artifactId = "month-calendar"
                version = "1.0"

                from(components["release"])
            }
        }*/
    }


    /*afterEvaluate {
        publishing {
            publications {
                register<MavenPublication>("release") {
                    groupId = "com.androidai.widget"
                    artifactId = "month-calendar"
                    version = "1.0"

                    afterEvaluate {
                        from(components["release"])
                    }
                }
            }
        }
    }*/

    dependencies {

        implementation(libs.core.ktx)
        implementation(libs.appcompat)
        implementation(libs.material)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.ext.junit)
        androidTestImplementation(libs.espresso.core)
        implementation(libs.glance)
    }
}

afterEvaluate {
    publishing{
        publications {
            create<MavenPublication>("release") {
                groupId = "com.androidai.app.widget"
                artifactId = "month-calendar"
                version = "1.0.0"

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}