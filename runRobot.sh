#!/bin/env bash # Environment
set -euo pipefail # Error handling and Strict mode

# Configuration file to save and read options
CONFIG_FILE=".runRobot_last_run_options"

# Function to get user input with the default value
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

# Function to save options to a config file
save_environment_values_for_intellij() {
  # Define the file to modify
  FILE=".idea/workspace.xml"

  # Define a regex pattern and replacement
  PATTERN='<option name=\"VM_PARAMETERS\" value=\"-Dcucumber.filter.tags=\~@ignore -Denvironment=[[:alpha:]]{2,3}\" \/>'
  REPLACEMENT='<option name=\"VM_PARAMETERS\" value=\"-Dcucumber.filter.tags=\~@ignore -Denvironment='$1'\" />'
  sed -E "s#$PATTERN#$REPLACEMENT#g" "$FILE" > temp.xml && mv temp.xml "$FILE"

  PATTERN='<option name=\"GLUE\" value=\".*\" \/>'
  REPLACEMENT='<option name=\"GLUE\" value=\"net.serenitybdd.cucumber.actors com.nuvei.features.steps\" />'
  sed -E "s#$PATTERN#$REPLACEMENT#g" "$FILE" > temp.xml && mv temp.xml "$FILE"

  PATTERN='<option name=\"MAIN_CLASS_NAME\" value=\".*\" \/>'
  REPLACEMENT='<option name=\"MAIN_CLASS_NAME\" value=\"net.serenitybdd.cucumber.cli.Main\" />'
  sed -E "s#$PATTERN#$REPLACEMENT#g" "$FILE" > temp.xml && mv temp.xml "$FILE"
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

check_if_appium_is_running() {
  # Define the Appium process name
  HTTP_STATUS_OK_CODE="200"
  HTTP_STATUS_RESOURCE_NOT_FOUND_CODE="404"

  APPIUM_URL="http://127.0.0.1:4723/status"

  CURRENT_HTTP_STATUS_CODE=$(curl -s -o /dev/null -w "%{http_code}" "$APPIUM_URL" 2>&1) || CURRENT_HTTP_STATUS_CODE="$HTTP_STATUS_RESOURCE_NOT_FOUND_CODE"

  # Check if the Appium process is running
  if  [ "$CURRENT_HTTP_STATUS_CODE" != "$HTTP_STATUS_OK_CODE"  ]; then
    echo ""
    echo "Appium is down. Please start Appium before running the tests."
    echo ""
    exit 1
  fi

}

# Main script
check_if_appium_is_running

# Load previous options if available
load_options

# Get environment selection
environment=$(get_input "Select environment (1: US | 2: MUS | 3: CA | 4: MCA | 5: UK | 6: PUK | 7: DE | 8: ES | 9: IT): " "${environment:-1}")

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
    env_code="puk"
    ;;
  7)
    env_code="de"
    ;;
  8)
    env_code="es"
    ;;
  9)
    env_code="it"
    ;;
  *)
    echo "Invalid environment selection."
    exit 1
    ;;
esac

# Get clean option
clean_gradle=$(get_input "Rebuild (clean) the entire project (Y/n): " "${clean_gradle:-Y}")

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
save_environment_values_for_intellij $env_code

# Prevent the Mac from going to sleep
killall caffeinate > /dev/null 2>&1 || true
caffeinate -d &

# Execute Gradle command
echo ""
echo "Executing: ./gradlew $clean_gradle_flag test --rerun-tasks $fail_fast_flag -Denvironment=$env_code"
echo ""
./gradlew $clean_gradle_flag test --rerun-tasks $fail_fast_flag -Denvironment=$env_code
gradlewstatus=$? # Save the exit status of the previous command

# Revert the mac from going to sleep
killall caffeinate
killall caffeinate > /dev/null 2>&1 || true
killall caffeinate > /dev/null 2>&1 || true

echo "Test report is available at: build/reports/tests/test/index.html"
open build/reports/tests/test/index.html

exit $gradlewstatus
