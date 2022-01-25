#!/bin/sh
echo "Running ktlint check"

git diff --name-only --cached --relative | grep '\.kt[s"]\?$' | xargs ./gradlew ktlintFile --args=

status=$?

if [ $status -ne 0 ]; then exit 1; fi
