import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs.plugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

// Lê os dados do arquivo
val apiKeyPropertiesFile = rootProject.file("apikey.properties")
// Cria um objeto Properties do Kotlin
val apiKeyProperties = Properties()
// Carrega os dados do arquivo para o objeto
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))

android {
    namespace = "com.emanuel.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.emanuel.weatherapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Os valores salvos dentro de apikeyProperties são armazenados com CHAVE-VALOR
        // criamos as buildConfigs definindo: o tipo, o nome que queremos e passando
        // o nome da key salvo lá no apikey.properties.
        // Obs: Tem que ser exatamente o mesmo nome se não
        buildConfigField("String", "API_KEY", apiKeyProperties["API_KEY"].toString())
        buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Navigation Component
    implementation(libs.navigation.fragment.ktx)

    // ViewModel e LiveData
    implementation(libs.viewmodel)
    implementation(libs.livedata)

    // Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.android.compiler)

    // Coroutine
    implementation(libs.coroutine)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)

    // Okhttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}