#!/usr/bin/env bash

# get lines from the PiTest build log that can help getting an overview of the test results
#
cat ../testLog.txt | egrep "\[INFO\] Building|>> Generated|>> Ran|> KILLED|> MEMORY_ERROR|> NO_COVERAGE|\[INFO\] Total time:|\[INFO\] Finished at:" > testSummary.txt
