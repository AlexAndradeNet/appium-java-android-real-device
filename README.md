# Appium + Java + Android - 2024

This project utilizes the **Screenplay Design Pattern** to help maintain the
SOLID principles, making the code more modular, readable, and maintainable.

For a similar setup targeting iOS (Apple iPhone), check out
the [Appium + Java + iOS - 2023](https://github.com/AlexAndradeNet/appiumjavaios)
repository.

This project is based on the excellent work
by [Jacobvu84 on Serenity Screenplay with Appium](https://github.com/Jacobvu84/serenity-screenplay-appium/).

---

## 🔧 Tech Stack

This project is built using the following technologies:

- **Java 21 (Temurin)**.
- **Gradle**.
- **Appium >= 2.13.1**.
- **Appium Inspector**.
- **Android SDK**.

For detailed setup instructions, please refer to
the [INSTALLATION.md](docs/INSTALLATION.md) file.

---

## 👍🏽 Contributing

We welcome contributions! If you’re interested in contributing to this
repository, please check out our [Contributing Guide](docs/CONTRIBUTE-java.md)
for all the necessary information to get started.

---

## 🚀 Running the Project

Follow these steps to run the tests on an Android device:

1. **Connect the Android Device**: Connect your Android device either by USB or
   Wi-Fi (recommended). The Wi-Fi connection is a little bit slow, but it's
   **more stable**. For detailed instructions, refer to the specific section in
   the [INSTALLATION.md](docs/INSTALLATION.md) file.

2. **Start the Appium Server**: Run the following command in the terminal while
   in the **root directory of the project**:

   ```bash
   appium
   ```

3. **Run the Tests**: There are two ways to run the tests. You can run them from
   the terminal using Gradle or directly from IntelliJ. Detailed instructions
   are provided below.

### Running from Gradle / Terminal

1. Open a terminal.
2. Navigate to the root directory of the project.
3. Execute the following command to clean and run the tests:

   ```bash
   sh runRobot.sh
   ```

### Running a Feature from IntelliJ

#### Customize Scenario Execution Template

1. Go to the menu **Run > Edit Configurations > Edit Configuration Templates >
   Cucumber Java**.
2. In the 'Edit Configuration Settings' window, set the **main class** to
   `net.serenitybdd.cucumber.cli.Main`.
3. Set the **Glue** field to the root package of your project (or the location
   of your step definitions): `com.nuvei.features.steps`.
4. Set the **VM Options** to
   `-Dcucumber.filter.tags=~@ignore -Denvironment=uk`.
5. Click **Apply**.
6. For more details,
   visit [John Ferguson Smart's blog on running Cucumber Serenity feature files in IntelliJ](https://johnfergusonsmart.com/running-cucumber-serenity-feature-files-directly-intellij/).

#### Executing a Feature or Scenario

1. Open the feature file you want to run and click on the **Run** icon
   (green "Play" triangle) next to the feature or scenario you want to execute.

---

## 📊 Reporting

Test execution reports are generated in the `build/reports/tests/test`
directory. To open the report in your browser:

- **On Mac:**

  ```bash
  open build/reports/tests/test/index.html
  ```

- **On Windows:**

  ```powershell
  start build/reports/tests/test/index.html
  ```

---

## 💅🏽 Linting / Coding Style

Maintaining a consistent coding style is crucial for readability and
collaboration.

> "The ratio of time spent reading versus writing is over 10 to 1. We are
> constantly reading old code as part of the effort to write new code. …making
> it easy to read makes it easier to write."
>
> _Robert C. Martin (a.k.a. Uncle Bob)_

### Java

Java files are formatted using the Google Java Format rules. Use the following
commands to check and fix the code format:

- **To check the code:**

  ```bash
  ./gradlew spotlessCheck
  ```

- **To fix the code:**

  ```bash
  ./gradlew spotlessApply
  ```

### Linting Gherkin Files

Gherkin files are linted using the `gherkin-lint` tool. The configuration is
available in the `.gherkin-lintrc` file. Use the following command to lint the
Gherkin files:

```bash
sh scripts/gherkin_check.sh
```

---

## Troubleshooting

The most common issues when running the tests are related to the Appium server and the executable at terminal level, sometimes the are outdated. Solve it with the following commands:

```bash
adb -s 192.168.2.126:5037 uninstall io.appium.uiautomator2.server
adb -s 192.168.2.126:5037 uninstall io.appium.uiautomator2.server.test
```

Happy coding! 🚀
