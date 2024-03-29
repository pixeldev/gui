## Installation

You can add `gui-api` to your project using [Gradle](https://gradle.org/)
*(recommended)*, [Maven](https://maven.apache.org/) or manually downloading the
JAR files.

If you want to use skull api you should add `gui-skull-api` subproject to your project.

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
  <artifactId>gui-item-api</artifactId>
  <version>VERSION</version>
</dependency>
```

### **Gradle Groovy DSL Dependency**
Add the repositories into your `repositories` section (`build.gradle`)
```kotlin
maven {
  name = 'unnamed-public'
  url = 'https://repo.unnamed.team/repository/unnamed-public/'
}
```
Add the dependency into your `dependencies` section (`build.gradle`)
```kotlin
implementation 'team.unnamed:gui-item-api:VERSION'
```

### **Gradle Kotlin DSL Dependency**
Add the repositories into your `repositories` section (`build.gradle.kts`)
```kotlin
maven("https://repo.unnamed.team/repository/unnamed-public/")
```
Add the dependency into your `dependencies` section (`build.gradle.kts`)
```kotlin
implementation("team.unnamed:gui-item-api:VERSION")
```
