# Appium + Java + Android - 2023

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

- **Java 17**
- **Appium 2.x**
- **Serenity BDD**
- **Cucumber**
- **Gradle**
- **Android 13**
- **Android SDK**
- **IntelliJ IDEA**

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
   Wi-Fi. Refer to the specific section in
   the [INSTALLATION.md](docs/INSTALLATION.md) file for detailed instructions.

2. **Start the Appium Server**:

   ```bash
   appium --allow-insecure=true --allow-cors --session-override
   ```

3. **Run the Tests**: There are two ways to run the tests. You can choose to run
   them from the terminal using Gradle or directly from IntelliJ. Detailed
   instructions are provided below.

### Running from Gradle / Terminal

1. Open a terminal.
2. Navigate to the root directory of the project.
3. Execute the following command to clean, run the tests, and generate an
   aggregated report:

   ```bash
   ./gradlew clean test aggregate
   ```

### Running a Feature from IntelliJ

1. Open the `feature` file you want to run.
2. Go to the **Run** menu and select **Run...**.
3. In the contextual menu, select the feature, then choose **Edit...**.
4. In the 'Edit Configuration Settings' window, set the main class to
   `net.serenitybdd.cucumber.cli.Main`.
5. Set the Glue field to the root package of your project (or the location of
   your step definitions).
6. Click **Apply**.

For more details,
visit [John Ferguson Smart's blog on running Cucumber Serenity feature files in IntelliJ](https://johnfergusonsmart.com/running-cucumber-serenity-feature-files-directly-intellij/).

---

## 📊 Reporting

Test execution reports are generated in the `target/site/serenity` directory. To
open the report in your browser:

- **On Mac:**

  ```bash
  open target/site/serenity/index.html
  ```

- **On Windows:**

  ```powershell
  start target/site/serenity/index.html
  ```

---

## 💅🏽 Linting / Coding Style

Maintaining a consistent coding style is crucial for readability and
collaboration.

> "The ratio of time spent reading versus writing is well over 10 to 1. We are
> constantly reading old code as part of the effort to write new code. …making it
> easy to read makes it easier to write."
>
> _Robert C. Martin (a.k.a Uncle Bob)_

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
sh gherkin_check.sh
```
