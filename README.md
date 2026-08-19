# Harmonia Music
This is a music app where you can play along with music and write your own music

## Getting Started

### Prerequisites

| Requirement | Notes |
|---|---|
| [JDK 11](https://adoptium.net/temurin/releases/?version=11) | The project is compiled with `--release 11`. Later JDK versions have not been validated against this project's JavaFX and Maven plugin versions. |
| [Apache Maven](https://maven.apache.org/download.cgi) 3.6 or later | Not required if using an IDE with bundled Maven support (e.g., IntelliJ IDEA). VS Code users should install the *Extension Pack for Java*. |

### Running the Application

#### Option 1: Command Line (Recommended)

The project's `pom.xml` is explicitly configured for this workflow via the `javafx-maven-plugin`, which resolves the correct platform-specific JavaFX runtime automatically. This is the most reliable method across operating systems.

```bash
git clone https://github.com/JIMorris/harmonia-music.git
cd harmonia-music
mvn clean javafx:run
```

#### Option 2: From an IDE

1. Open the cloned project directory in an IDE with Java and Maven support (IntelliJ IDEA, VS Code with the *Extension Pack for Java*, or Eclipse).
2. Allow the IDE to import the Maven project and resolve dependencies; this may take a few minutes on first run.
3. Open `src/main/java/com/harmoniamusic/App.java` and launch the `main` method using the IDE's built-in run functionality.

This approach works because the project includes a `module-info.java`, which enables compatible IDEs to infer the JavaFX module path without additional configuration. Behavior may vary across IDEs and operating systems; if you encounter a `JavaFX runtime components are missing` error, use the command-line method described above instead.
## Backend Presentation
https://youtu.be/qfJ3y4Mh0k4

## Prototype
https://jimorris.github.io/harmonia-music/

## Prototype Presentation
https://youtu.be/3JlOhOzbgMc

## Frontend Presentation
https://youtu.be/FEhAVRWip8Y
