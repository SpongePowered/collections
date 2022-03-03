# collections
![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/SpongePowered/collections/build/master) [![MIT License](https://img.shields.io/badge/license-MIT-blue)](LICENSE.txt) [![Maven Central](https://img.shields.io/maven-central/v/org.spongepowered/collections?label=stable)](https://search.maven.org/search?q=g:org.spongepowered%20AND%20a:collections) ![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/org.spongepowered/collections?label=dev&server=https%3A%2F%2Foss.sonatype.org)


* [Source]
* [Issues]
* [Community Discord]

## Prerequisites
* Java 8

## Building from Source
In order to build collections you simply need to run the `./gradlew build` command. You can find the compiled JAR file in `./build/libs` labeled similarly to 'collections-x.x.x-SNAPSHOT.jar'.

## Contributing
Are you a talented programmer looking to contribute some code? We'd love the help!
* Open a pull request with your changes, following our [guidelines](CONTRIBUTING.md).

## Usage

collections publishes releases on Maven Central and Sponge's own repository.
Snapshots are published on Sonatype OSSRH and Sponge's repository.

If you're using [Gradle] to manage project dependencies, simply include the following in your `build.gradle` file:
```gradle
repositories {
  // releases
  mavenCentral()
  // snapshots
  maven {
    url "https://repo.spongepowered.org/repository/maven-public/"
  }
}

dependencies {
  implementation "org.spongepowered:collections:2.0.0-SNAPSHOT"
}
```
```kotlin
repositories {
    // releases
    mavenCentral()
    // snapshots
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

// Can be declared in line
val collectionsVersion: String = "1.0.0-SNAPSHOT"
// Or pulled from gradle.properties file
val collectionsVersion: String by project

dependencies {
    implementation("org.spongepowered:collections:$collectionsVersion")
}
```

If you're using [Maven] to manage project dependencies, simply include the following in your `pom.xml` file:
```xml
<dependency>
  <groupId>org.spongepowered</groupId>
  <artifactId>collections</artifactId>
  <version>2.0.0-SNAPSHOT</version>
</dependency>
```

## Credits


[Gradle]: https://gradle.org
[Maven]: https://maven.apache.org/
[Source]: https://github.com/SpongePowered/collections
[Issues]: https://github.com/SpongePowered/collections/issues
[License]: https://opensource.org/licenses/MIT
[Community Discord]: https://discord.gg/sponge
