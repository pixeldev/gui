# **gui**

**THIS PROJECT IS BEING RE-CODED BECAUSE IT HAS A LOT OF BUGS AND ERRORS**

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
  <groupId>team.unnamed.gui</groupId>
  <artifactId>core</artifactId>
  <version>VERSION</version>
</dependency>
```

### **Gradle Dependency**
Add the repositories into your `repositories` section (`build.gradle`)
```groovy
maven {
  name = 'unnamed-public'
  url = 'https://repo.unnamed.team/repository/unnamed-public/'
}
```
Add the dependency into your `dependencies` section (`build.gradle`)
```groovy
implementation 'team.unnamed.gui:core:VERSION'
```
