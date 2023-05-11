
# Venly Assessment Task

## Requirements
To build and run the application, please ensure you have the following installed:

1. [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. [Maven 3](https://maven.apache.org)

To create an executable jar, using maven, you can run:

``` shell
mvn clean install
```


## Running the application locally
Just like every other spring boot application, the easiest way to run this spring boot application is to execute
the `main` method in the `com.venly.task.TaskApplication` class from an IDE.

Furthermore, the application's jar file created by running
``` shell
mvn clean install
``` 
can be executed from the command line/terminal using the java run command like so:

```shell
java -jar task-0.0.1-SNAPSHOT.jar
```

Finally, the application can also be run using the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

``` shell
mvn spring-boot:run
```


