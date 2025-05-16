BrainTeaserA Simple Flashcard Quiz GameBrainTeaser is a fun and engaging Android application built using Kotlin and Jetpack Compose. It's designed to test your general knowledge with a series of true/false questions presented in a flashcard format. The app features a clean, modern UI with smooth transitions between screens.FeaturesSplash Screen: A visually appealing introduction to the app with a gradient background and logo.Start Screen: 

A welcoming screen with the app logo, a prompt, and a button to begin the quiz.Quiz Screen: Displays questions with accompanying images, a countdown timer, progress indicator, and True/False answer buttons. Provides instant feedback on answers.Score Screen: Shows the user's final score, indicates if they passed (based on a simple threshold), and offers options to play again or exit.State-Driven Navigation: Seamless transitions between screens managed by Jetpack Compose state.Consistent Styling: Centralized color definitions and a reusable header component ensure a uniform look and feel across the application.No External Libraries: 

Built using only core Android and Jetpack Compose libraries as per project requirements.ScreenshotsHere are some screenshots of the application screens:Splash Screen: (Refer to iPhone 16 Pro - 1.png)Start Screen: (Refer to Screenshot 2025-05-05 131016.png)Quiz Screen: (Visual representation of the quiz interface with image, timer, progress, and buttons)Score Screen: (Visual representation of the score display with circle, progress bar, and result message)(Note: Actual image files are not included in this README text, but you can add them to your repository and link them here using Markdown image syntax ![Alt text](path/to/image.png))Getting StartedPrerequisitesAndroid Studio (Latest Version Recommended)Kotlin Plugin for Android StudioAn Android device or emulator to run the appInstallationClone or download the project repository to your local machine.

Open the project in Android Studio.Ensure you have the necessary Android SDKs installed (refer to the project's build.gradle files for target and compile SDK versions).Connect an Android device or start an emulator.Click the "Run" button in Android Studio to build and install the app.Project StructureThe project follows a standard Android project structure with key components organized as follows:BrainTeaser/

├── app/
│   └── src/
│       └── main/
│           └── java/
│               └── com/
│                   └── reeper/
│                       └── brainteaser/
│                           ├── MainActivity.kt         # Main Activity and Navigation Host
│                           ├── SplashScreen.kt         # Composable for the Splash Screen
│                           ├── StartScreen.kt          # Composable for the Start Screen
│                           ├── QuizScreen.kt           # Composable for the Quiz Screen
│                           ├── ScoreScreen.kt          # Composable for the Score Screen
│                           └── ui/
│                               └── theme/
│                                   ├── Color.kt        # Centralized color definitions (AppColors)
│                                   ├── Theme.kt        # Material Theme definition
│                                   ├── Type.kt         # Typography definition (if used)
│                                   └── AppComponents.kt  # Reusable Composable components (e.g., AppHeader)
│           └── res/
│               ├── drawable/       # Image assets (logo, icons, question images)
│               ├── layout/         # (Not used with Compose)
│               ├── mipmap/         # Launcher icons
│               └── values/         # Resources (colors, strings, themes - though themes are in ui.theme)
└── gradle/
└── build.gradle.kts        # Project level build file
└── app/build.gradle.kts    # Module level build file

Key ComponentsMainActivity.kt: The entry point of the application. It sets up the Jetpack Compose environment and contains the AppNavigationHost composable which manages the different screens using a sealed class Screen and state management.SplashScreen.kt: Displays the initial splash screen with a timed delay before navigating to the start screen.StartScreen.kt: 

The first interactive screen, presenting the app's purpose and a button to begin the quiz.QuizScreen.kt: Contains the core quiz logic. It displays questions, manages the timer, handles user input (True/False), tracks the score, and determines when the quiz is complete to navigate to the score screen.ScoreScreen.kt: Displays the final score and provides options to restart the quiz or navigate home.ui.theme/Color.kt: Defines the AppColors object, centralizing all the custom colors used throughout the application for consistency.ui.theme/AppComponents.kt: Contains reusable UI components like the AppHeader composable, reducing code duplication.ui.theme/Theme.kt: Defines the Material Theme for the application, optionally integrating AppColors for a more cohesive theme definition.Styling and ThemingThe application utilizes Jetpack Compose's theming capabilities. Custom colors are defined in the AppColors object in ui.theme/Color.kt and used explicitly in the screen composables. 

A reusable AppHeader component is used across multiple screens to maintain a consistent top section layout.NavigationNavigation is handled internally within the AppNavigationHost composable in MainActivity.kt using a mutableStateOf<Screen> variable. Screen transitions occur by updating this state variable based on user actions (e.g., clicking "Start", finishing the quiz) or timed events (splash screen timeout).Future EnhancementsMore Questions: Expand the sampleQuestions list with more diverse flashcard questions.Question Categories: Implement different categories of questions.Difficulty Levels: Add varying difficulty levels with different timers or question types.User Profiles: Allow users to enter their name and track scores persistently.Score History: Store past quiz scores.

Improved UI/UX: Further refine the visual design and user experience with animations, transitions, and more detailed feedback.Settings Screen: Add options for customizing quiz duration, categories, etc.Passing Threshold: Make the passing score threshold configurable.LicenseThis project is licensed under the MIT License - see the LICENSE file (if you create one) for details.(Note: You may want to create a LICENSE file in your project's root directory with the actual MIT License text)
