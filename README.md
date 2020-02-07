# Mutation testing with PiTest

Shows how to use [Pitest](https://pitest.org/).
 
To add PiTest to your project you need to add the following in your pom.xml file:

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.pitest</groupId>
                <artifactId>pitest-maven</artifactId>
                <version>1.4.3</version>
                <dependencies>
                    <dependency>
                        <groupId>org.pitest</groupId>
                        <artifactId>pitest-junit5-plugin</artifactId>
                        <version>0.10</version>
                    </dependency>
                </dependencies>
            </plugin>
    ...
```

and run the tests:

```bash
    mvn clean test org.pitest:pitest-maven:mutationCoverage
```

The results are in `target/pit-reports`.
