# Appium + Java + Android - 2023

This project is written with the **Screenplay design pattern** that helps to
maintain the SOLID principles.

You can find a version for iOS (Apple iPhone)
here: [Appium + Java + iOS - 2023](https://github.com/AlexAndradeNet/appiumjavaios).

This project is based
on https://github.com/Jacobvu84/serenity-screenplay-appium/

---

## 🔧 Tech stack

This project uses the following technologies:

* Java 17
* Appium 2.x
* Serenity BDD
* Cucumber
* Gradle
* Android 13
* Android SDK
* IntelliJ IDEA

If you need help to install the environment, you can find the instructions in
this file [INSTALLATION.md](docs/INSTALLATION.md).

---

## 🚀 Running the project

1. Connect the Android device either by USB or Wi-Fi. Check a specific section
   for this in the file [INSTALLATION](docs/INSTALLATION.md) for more details.
2. Run the Appium server:

    ```bash
    appium --allow-insecure=true --allow-cors --session-override
    ```

3. Run the tests. See the two next sections for more details.

### Running from Gradle / Terminal

1. Open a terminal
2. Navigate to the project root
3. Run the following command:
    ```bash
    ./gradlew clean test aggregate
    ```

### Running feature from IntelliJ

1. Click on the `feature` file you want to run
2. In the Run menu Select Run...
3. In the contextual menu, select the feature, then "Edit..."
4. You should now see the 'Edit Configuration Settings' window. Set the main
   class to `net.serenitybdd.cucumber.cli.Main`
5. Change the Glue field to the root package of your project (or of your step
   definitions)
6. Click Apply

Source: https://johnfergusonsmart.com/running-cucumber-serenity-feature-files-directly-intellij/

---

## 📊 Reporting

The reports are generated in the `target/site/serenity` folder.

If you are in Mac, you can run the following command to open the report in your
browser:

```bash
open target/site/serenity/index.html
```

If you are in Windows, you can run the following command to open the report in
your browser:

```powershell
start target/site/serenity/index.html
```

---

## 💅🏽 Linting / Coding style

Coding style is very important. It helps to maintain the code clean and easy to
read.

> The ratio of time spent reading versus writing is well over 10 to 1. We are
> constantly reading old code as part of the effort to write new code. …making
> it
> easy to read makes it easier to write.

_Robert C. Martin (a.k.a Uncle Bob)_

### Java

The Java files are linted using the Google Java Format rules.
Run the following command to lint the Java files:

```bash
# To check the code
./gradlew clean build spotlessCheck
# To fix the code
./gradlew clean build spotlessApply
```

### Linting Gherkin files

The Gherkin files are linted using the `gherkin-lint` tool. The configuration
file is located in the `.gherkin-lintrc` file.
Run the following command to lint the Gherkin files:

```bash
sh gherkin_check.sh
```
