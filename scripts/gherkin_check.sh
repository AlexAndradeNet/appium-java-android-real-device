#!/bin/env bash # Environment
set -euo pipefail # Error handling and Strict mode

npx gherkin-lint -c .gherkin-lintrc.json ./src
lastCommandResult=$?

if [ $lastCommandResult -ne 0 ]; then
  echo "Gherkin lint failed"
  exit 1
fi

echo "Gherkin lint passed"
exit 0
