#!/bin/sh

echo "pre-commit: About to run the checkstyle linter..."
echo
mvn checkstyle:checkstyle
echo

file=$(<target/checkstyle-result.xml)

if [[ "$file" == *"<error"* ]]; then
  echo "There are linting errors. Please check the report (target/site/checkstyle.html)"
  echo
  exit 1
else
  echo "No linting errors"
  echo
  exit 0
fi