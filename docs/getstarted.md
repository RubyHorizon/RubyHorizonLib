# Get started

1. [Gradle](#Gradle)
2. [Maven](#Maven)

# Gradle
1. Go to [GitHub token generation page](https://github.com/settings/tokens/new).
2. Generate your token with *read:packages* and *repo* scopes.
3. Go to
    ```
    ~/.gradle/.gradle.properties
    ```
    in your plugin project dir.
4. Put this settings in file
   ```
   USERNAME=YOUR_GITHUB_USERNAME
   TOKEN=YOUR_GITHUB_TOKEN_FROM_2_STEP
   ```

gradle (groovy dsl)

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/RubyHorizon/RubyHorizonLib")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    compileOnly('team.rubyhorizon.lib:api:VERSION')
}
```

gradle (kotlin dsl)
```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/RubyHorizon/RubyHorizonLib")
        credentials {
            username = findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    compileOnly("team.rubyhorizon.lib:api:VERSION")
}

```
# Maven

1. Go to [GitHub token generation page](https://github.com/settings/tokens/new).
2. Generate your token with *read:packages* and *repo* scopes.
3. Go to
    ```
    ~/.m2/settings.xml
    ```
   in your plugin project dir.
4. Put this settings in file
    ```xml
    <servers>
      <server>
        <id>github</id>
        <username>YOUR_GITHUB_USERNAME</username>
        <password>YOUR_GITHUB_TOKEN_FROM_2_STEP</password>
      </server>
    </servers>
    ```

```xml
<repositories>
  <repository>
    <id>github</id>
    <name>GitHub Packages</name>
    <url>https://maven.pkg.github.com/RubyHorizon/RubyHorizonLib</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>team.rubyhorizon.lib</groupId>
    <artifactId>api</artifactId>
    <version>VERSION</version>
  </dependency>
</dependencies>

```

[Back to information page](info.md)