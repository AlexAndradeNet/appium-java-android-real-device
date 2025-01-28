#!/bin/env bash # Environment
set -euo pipefail # Error handling and Strict mode

# Configuration file to save and read options
CONFIG_FILE=".runRobot_last_run_options"

# Function to get user input with default value
get_input() {
  local prompt="$1"
  local default_value="$2"

  read -p "$prompt ($default_value): " input

  if [ -z "$input" ]; then
    input="$default_value"
  fi

  echo "$input"
}

# Function to save options to a config file
save_options() {
  echo "environment=$environment" > "$CONFIG_FILE"
  echo "clean_gradle=$clean_gradle" >> "$CONFIG_FILE"
  echo "fail_fast=$fail_fast" >> "$CONFIG_FILE"
}

# Function to load options from a config file
load_options() {
  if [[ -f "$CONFIG_FILE" ]]; then
    source "$CONFIG_FILE"
    echo "Loaded previous options:"
    echo "  Environment: $environment"
    echo "  Clean Gradle: $clean_gradle"
    echo "  Fail Fast: $fail_fast"
  else
    echo "No previous options found. Defaults will be used."
  fi
}

# Load previous options if available
load_options

# Get environment selection
environment=$(get_input "Select environment (1: US | 2: MUS | 3: CA | 4: MCA | 5: UK | 6: DE | 7: ES | 8: IT): " "${environment:-1}")

case $environment in
  1)
    env_code="us"
    ;;
  2)
    env_code="mus"
    ;;
  3)
    env_code="ca"
    ;;
  4)
    env_code="mca"
    ;;
  5)
    env_code="uk"
    ;;
  6)
    env_code="de"
    ;;
  7)
    env_code="es"
    ;;
  8)
    env_code="it"
    ;;
  *)
    echo "Invalid environment selection."
    exit 1
    ;;
esac

# Get clean option
clean_gradle=$(get_input "Rebuild (clean) the entire project (Y/n): " "${clean_gradle:-N}")

if [[ "$clean_gradle" != "n" && "$clean_gradle" != "N" ]]; then
  clean_gradle_flag="clean"
else
    clean_gradle_flag=""
fi

# Get fail-fast option
fail_fast=$(get_input "Stop at first error (fail-fast) (Y/n): " "${fail_fast:-N}")

if [[ "$fail_fast" != "n" && "$fail_fast" != "N" ]]; then
  fail_fast_flag="--fail-fast"
else
    fail_fast_flag=""
fi

# Save current options for next execution
save_options

# Prevent the mac from going to sleep
killall caffeinate > /dev/null 2>&1
caffeinate -d &

# Execute Gradle command
echo "Executing: ./gradlew $clean_gradle_flag test --rerun-tasks $fail_fast_flag -Denvironment=$env_code"
./gradlew $clean_gradle_flag test --rerun-tasks $fail_fast_flag -Denvironment=$env_code
gradlewstatus=$? # Save the exit status of the previous command

# Revert the mac from going to sleep
killall caffeinate

echo "Test report is available at: build/reports/tests/test/index.html"

exit $gradlewstatus
