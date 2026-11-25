# Kotlin Library Management System

A console-based Library Management System built with **Kotlin** and **Gradle**. The application demonstrates a layered design with models, a repository, a service layer, and a menu-driven console interface.

## Features

- Add and list books.
- Register and list library patrons.
- Borrow available books and return borrowed books.
- Enforce a maximum of three borrowed books per patron.
- Search books by title or author (case-insensitive).
- Report clear borrowing outcomes, including missing books or patrons, unavailable books, and borrowing-limit violations.
- Seed the console application with sample books and a patron.

The application uses in-memory storage. Data is reset when the program is restarted.

## Technology

- Kotlin/JVM
- Gradle Wrapper with Kotlin DSL
- JDK 21 toolchain
- JUnit Jupiter for tests

## Project structure

```text
kotlin__gradle_project/
├── app/
│   └── src/
│       ├── main/kotlin/org/dbu/library/
│       │   ├── model/        Book and Patron models
│       │   ├── repository/   Repository interface and in-memory storage
│       │   ├── service/      Library operations and borrowing results
│       │   ├── ui/           Console menu and application entry point
│       │   └── util/         Utility extensions
│       └── test/kotlin/      JUnit tests
├── gradle/
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── app/build.gradle.kts
```

## Prerequisites

- JDK 21 (the Gradle build uses a Java 21 toolchain).
- Git.

The included Gradle Wrapper downloads and uses the configured Gradle version, so a separate Gradle installation is not normally required.

## Clone the repository

Run in Windows Command Prompt:

```bat
git clone https://github.com/kura458/kotlin__gradle_project.git
cd kotlin__gradle_project
```

## Build and test

On Windows:

```bat
gradlew.bat clean build
gradlew.bat test
```

On Linux or macOS:

```bash
./gradlew clean build
./gradlew test
```

## Run the application

On Windows:

```bat
gradlew.bat run
```

On Linux or macOS:

```bash
./gradlew run
```

The program opens a console menu. Follow the numbered options and enter the requested book, patron, or ISBN details.

## Build a runnable JAR

```bat
gradlew.bat jar
```

The JAR is generated in:

```text
app/build/libs/
```

The Gradle build configures a main class and includes runtime dependencies in the JAR. Run the generated JAR with:

```bat
java -jar app\build\libs\app.jar
```

If the generated filename differs, use the actual JAR name shown in app/build/libs/.

## Design overview

- **Model:** represents books and patrons.
- **Repository:** stores and retrieves records in memory.
- **Service:** enforces library rules such as availability and borrowing limits.
- **Console UI:** displays the menu and handles user input.

This separation keeps business logic independent from the console interaction and makes the service layer easier to test.

## Current limitations and possible improvements

- Data is not persisted between runs.
- Input validation and error messages can be expanded.
- More edge-case tests would improve confidence in return, deletion, and repository behavior.
- A database-backed repository could replace in-memory storage in a future version.

## Author

Maintained by [kura458](https://github.com/kura458).
