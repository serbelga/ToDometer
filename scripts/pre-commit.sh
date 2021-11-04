#!/bin/sh
echo "Running ktlint check"

git diff --name-only --cached --relative | grep '\.kt[s"]\?$' | xargs ktlint --relative .

status=$?

if [ $? -ne 0 ]; then exit 1; fi