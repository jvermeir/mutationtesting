# Mutation testing

[Mutation testing](https://en.wikipedia.org/wiki/Mutation_testing) promises to help ensure quality tests. It does this by making changes to a code base and running the tests. If all is well, some changes in code should result in failing tests. So making a bunch of changes like reversing the conditions in an if-statement, should cause the tests to fail. If not, the test isn't good enough. 
The Wikipedia page above shows a list of frameworks that gave me pause; most of these frameworks are badly outdated. The most active projects I found are

- [Stryker](https://stryker-mutator.io/) - works for Javascript
- [Pitest](https://pitest.org/) 
- [Jumble](https://sourceforge.net/projects/jumble/files/jumble/1.3.0/) - Last updated in 2015 

Stryker works for JavaScript, C# and Scala, but not for Java. Jumble was mentioned on the Wikipedia page, but seems inactive, just like most other frameworks listed on the page. 

PITest should work fine for Java, so I decided to try it out. The code lives [here on GitHub](https://github.com/jvermeir/mutationtesting).

The class under test is `MyClass`. It defines a single method `myTestMethod`.

This method 

```
    public boolean myTestMethod(int i) {
        if (i > 10) return true;
        return false;
    }
```

makes a decision based on an input parameter. 

The test code in MyControllerTest shows what looks like a perfectly fine test:

```
    @Test
    public void testMyTestMethod() {
        MyClass myClass = new MyClass();
        assertTrue(myClass.myTestMethod(100));
        assertFalse(myClass.myTestMethod(0));
    }
```

Both branches of the if in `myTestMethod` are tested, but what happens if we would change the condition of the if?
The test cases are not sufficient because we can change `if (i > 10)` to `if (i > 11)` and all would still be green. 

PITest catches this problem in our test case:

```
1. changed conditional boundary → SURVIVED
```

```
2. negated conditional → KILLED
3. replaced return of integer sized value with (x == 0 ? 1 : 0) → KILLED
1. replaced return of integer sized value with (x == 0 ? 1 : 0) → KILLED
``` 

as shown by the line `1. changed conditional boundary → SURVIVED`. The boundary for the if statement can be changed but the test still passes. 
Given this test code it wasn't possible to negate the condition, because in that case the test fails:

```
2. negated conditional → KILLED
```

(OK, so this is where my wife, working as a tester, was rather unimpressed with my results and said 'duh, your test is broken because it didn't test for the boundary condition'. Fair enough, I guess... Still, these things happen, right?)

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
```
    mvn clean test org.pitest:pitest-maven:mutationCoverage
```

The results are in `target/pit-reports`.
