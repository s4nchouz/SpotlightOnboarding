# Spotlight Onboarding

[![compose-mp-version](https://img.shields.io/badge/compose--multiplatform-1.7.3-blue)](https://github.com/JetBrains/compose-multiplatform)
[![kotlin-version](https://img.shields.io/badge/kotlin-2.1.10-blue)](https://github.com/JetBrains/compose-jb)

![badge-iOS](https://img.shields.io/badge/Platform-iOS-lightgray)
![badge-Android](https://img.shields.io/badge/Platform-Android-brightgreen)
![badge-JVM](https://img.shields.io/badge/Platform-JVM-orange)
![badge-macOS](https://img.shields.io/badge/Platform-macOS-purple)
![badge-web](https://img.shields.io/badge/Platform-Web-blue)

Spotlight Onboarding is a library for Jetpack Compose and Compose Multiplatform that helps you create guided onboarding experiences by highlighting key UI elements. It allows users to focus on specific parts of your interface while dimming the rest of the screen.

## Features
- Highlight specific UI elements.
- Smooth enter/exit animations.
- Customizable scrim color and shape.
- Optional Material 3 BottomSheet support.

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
implementation("io.github.s4nchouz:spotlight-onboarding:<version>")
```

For the Material 3 BottomSheet version:

```kotlin
implementation("io.github.s4nchouz:spotlight-onboarding-bottom-sheet:<version>")
```

## Usage

### Basic Spotlight Onboarding

```kotlin
@Composable
fun MyScreen() {
    val spotlightState = rememberSpotlightOnboardingState()
    
    SpotlightOnboarding(
        isVisible = true,
        spotlightState = spotlightState
    ) {
        // Your content here
    }
}
```

### Highlighting Items

Use `spotlightItem` modifier to mark elements to be highlighted:

```kotlin
Text(
    text = "Highlighted Item",
    modifier = Modifier.spotlightItem()
)
```

### Using BottomSheet (Material 3)

```kotlin
SpotlightOnboarding(
    isVisible = true,
    spotlightState = spotlightState,
    onDismissRequest = { /* Handle dismiss */ },
    sheetContent = {
        Text("Additional Information")
    }
) {
    // Your content here
}
```

## Customization

- `scrimColor`: Customize the background overlay color.
- `spotlightEnterTransition`: Define how the spotlight appears.
- `spotlightExitTransition`: Define how the spotlight disappears.

## License

```text
Copyright 2025 Aleksandr Anokhin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
