# Mutation testing

[Muation testing is defined here](https://en.wikipedia.org/wiki/Mutation_testing)

## Frameworks

- [Stryker](https://stryker-mutator.io/) - works for Javascript
- [Pitest](https://pitest.org/) 
- [Jumble](https://sourceforge.net/projects/jumble/files/jumble/1.3.0/) - Last updated in 2015 

Stryker doesn't work for Java. Jumble was mentioned on the Wikipedia page, but seems inactive, just like most other frameworks mentioned on the page. 

PITest works fine though. 

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
The test cases are not sufficient because we can change `if (i > 10)` to `if (i > 11)` and all would be green. 

PITest catches this problem in our test case:

```
1. changed conditional boundary → SURVIVED
```

```
2. negated conditional → KILLED
3. replaced return of integer sized value with (x == 0 ? 1 : 0) → KILLED
1. replaced return of integer sized value with (x == 0 ? 1 : 0) → KILLED
``` 

as shown by the line `1. changed conditional boundary → SURVIVED`. 

## Run the tests

    mvn clean test org.pitest:pitest-maven:mutationCoverage
    
the results are in `target/pit-reports`.


## Part 2: performance test 

How efficient is mutation testing? To find out we'll generate a large number of classes and test code for each. Then, we'll run 
PITest and see how long it takes.

`testGenerator` contains the `TestCodeGenerator.java` which will generate test classes named `ClassUnderTestX.java` from a 
template named `ClassUnderTestTemplate.java`. The code is generated in a temporary folder created by JUnit's tempfolder feature. 
Then the project is build using maven with the `pom.xml` provided. 

Running the tests without piTest takes about 10 seconds.

```
[INFO] Results:
[INFO]
[INFO] Tests run: 1000, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 10.479 s
[INFO] Finished at: 2020-02-01T21:59:22+01:00
[INFO] Final Memory: 14M/57M
[INFO] ------------------------------------------------------------------------```
```

With mutations added the tests take 60 times longer (10 seconds vs 11 minutes).

```
================================================================================
- Timings
================================================================================
> scan classpath : < 1 second
> coverage and dependency analysis : 3 seconds
> build mutation tests : 1 seconds
> run mutation analysis : 11 minutes and 3 seconds
--------------------------------------------------------------------------------
> Total  : 11 minutes and 8 seconds
--------------------------------------------------------------------------------
================================================================================
- Statistics
================================================================================
>> Generated 9000 mutations Killed 4000 (44%)
>> Ran 5000 tests (0.56 tests per mutation)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 11:20 min
[INFO] Finished at: 2020-02-01T22:10:58+01:00
[INFO] Final Memory: 18M/70M
[INFO] ------------------------------------------------------------------------```
