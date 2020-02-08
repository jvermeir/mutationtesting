#!/usr/bin/env bash

# Run PiTest on part of a Maven project. Use the exclusions variable to list folders that should be skipped
#
exclusions="./pom.xml ./client/pom.xml"
files=$(find . -name "pom.xml")

echo log: > testLog.txt
for f in $files
do
    if [[ $exclusions == *$f* ]]; then
        echo skipping $f >> testLog.txt
    elif [[ $f == *target* ]]; then
        echo skipping $f >> testLog.txt
    else
        echo analysing $f >> testLog.txt
        mvn test -f $f org.pitest:pitest-maven:mutationCoverage
    fi
    echo DONE >> testLog.txt
done
