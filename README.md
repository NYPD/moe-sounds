![Moe Sounds Logo](https://raw.githubusercontent.com/NYPD/moe-sounds/master/WebContent/images/logo-size2.png)<br>
<sup>(Logo by [Louis Wabnitz @ studio peep](http://studiopeep.com/))</sup>

# moe-sounds
Moe sounds, moe problems. Check out the live [site.](http://www.moesounds.com)

## Build System
This project has been migrated from Maven to **Gradle**. The directory structure now follows standard Gradle conventions:
- Source code: `src/main/java/`
- Resources: `src/main/resources/`
- Web content: `src/main/webapp/`
- Test code: `src/test/java/`
- Test resources: `src/test/resources/`

### Building with Gradle
```bash
# Build the project
gradle build

# Run tests
gradle test

# Build WAR file
gradle war

# Clean build artifacts
gradle clean
```

The old `pom.xml` has been archived as `pom.xml.bak` for reference.

## Documentation
For information on how to setup your own moe sounds, read the uncompleted [wiki.](https://github.com/NYPD/moe-sounds/wiki)
