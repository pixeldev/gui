## Installation

You can add `gui` to your project using [Gradle](https://gradle.org/)
*(recommended)*, [Maven](https://maven.apache.org/) or manually downloading the
JAR files

To use general API you should add just `gui-menu-api` subproject to your project.
Since `gui` uses [adapters](../../menu/adapt) for each Minecraft version you should include corresponding
adapters in your project as well.

Using versions above 1.17 you should use Java 17 or above.

### **Maven Dependency**
Add the repositories into your `<repositories>` tag (`pom.xml`)
```xml
<repository>
  <id>unnamed-public</id>
  <url>https://repo.unnamed.team/repository/unnamed-public/</url>
</repository>
```
Add the dependency into your `<dependencies>` tag (`pom.xml`)
```xml
<dependency>
  <groupId>team.unnamed</groupId>
  <artifactId>gui-menu-api</artifactId>
  <version>VERSION</version>
</dependency>
```

### **Gradle Groovy DSL Dependency**
Add the repositories into your `repositories` section (`build.gradle`)
```groovy
maven {
  name = 'unnamed-public'
  url = 'https://repo.unnamed.team/repository/unnamed-public/'
}
```
Add the dependency into your `dependencies` section (`build.gradle`)
```groovy
implementation 'team.unnamed:gui-menu-api:VERSION'
```

### **Gradle Kotlin DSL Dependency**
Add the repositories into your `repositories` section (`build.gradle.kts`)
```kotlin
maven("https://repo.unnamed.team/repository/unnamed-public/")
```
Add the dependency into your `dependencies` section (`build.gradle.kts`)
```kotlin
implementation("team.unnamed:gui-menu-api:VERSION")
```
