# ANDROID INSTALLATION

Before running Android tests with Appium, some dependencies must be
pre-installed on your development machine. This guide will walk you through each
step.

Don't worry if some details are unclear initially—Appium abstracts much of this
complexity. You can always explore more specific capabilities of these libraries
later as needed.

---

## ✏️ Installation Steps

### Installing Android Tools

These instructions are for Mac OS X users. If you are using Windows, follow
the [official Android Studio installation guide](https://developer.android.com/studio/install).

> **Note:** The following steps provide a minimal setup to run tests. For a full
> Android Studio installation, refer to
> the [Android Studio setup guide](https://developer.android.com/studio/install)

1. **Install Basic Development Tools**
   Open a terminal and run the following commands to update Homebrew and install
   the necessary dependencies:

   ```bash
   # Silicon Macs
   brew update
   brew install gradle
   brew install node
   ```

2. **Install OpenJDK and Android Command Line Tools**

   Open a terminal and run the following commands to update Homebrew and install
   the necessary dependencies:

   ```bash
   # Silicon Macs
   brew install --cask temurin@21
   echo "export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home" >> ~/.zprofile
   source ~/.zprofile
   ```
   For Intel-Based Macs ⚠️:
    ```bash
   # Intel Macs
   brew install openjdk@21
   echo "export JAVA_HOME=/usr/local/opt/openjdk@17" >> ~/.zprofile
   source ~/.zprofile
   ```

3. **Set Up Environment Variables**

   Define the `ANDROID_SDK_ROOT` environment variable by adding the following
   line to your `~/.zprofile`:

   ```bash
   echo "export ANDROID_SDK_ROOT=/opt/homebrew/share/android-commandlinetools/" >> ~/.zprofile
   echo "export ANDROID_HOME=\$ANDROID_SDK_ROOT" >> ~/.zprofile
   source ~/.zprofile
   ```

4. **Install Android SDK Components**

   Install the essential Android SDK components using `sdkmanager`.

   ```bash
   # Silicon Macs
   sdkmanager "build-tools;34.0.0" "platform-tools" "emulator" "system-images;android-34;google_apis;arm64-v8a" "platforms;android-34"
   ```

   For Intel-Based Macs ⚠️:
    ```bash
   # Intel Macs
   sdkmanager "build-tools;34.0.0" "platform-tools" "emulator" "system-images;android-34;google_apis;x86_64" "platforms;android-34"
   ```

5. **Verify Installation**

   Run the following commands to verify your installation:

   ```bash
   # Verify installed components
   brew install android-platform-tools
   sdkmanager --list_installed
    ```

### Installing project dependencies

1. Execute the following command to install the project dependencies:

   ```bash
   ./gradlew build
   ```

### Installing Appium 2.x

If you previously installed Appium using Homebrew, uninstall it to avoid
conflicts. Additionally, remove any `appium-doctor` or `appium-desktop`
packages.

1. **Install Appium**

   Install the latest version of Appium globally using `npm`:

   ```bash
   npm i -g appium
   ```

2. **Install the Required WebDriver**

   After installing Appium, install the required WebDriver for Android:

   ```bash
   appium driver install uiautomator2
   ```

   Check the installation by running the following command:

    ```bash
    appium driver doctor uiautomator2
    ```

### Installing Appium Inspector

Appium Inspector is a graphical user interface (GUI) tool that simplifies
inspecting elements within your mobile app and retrieving their locators. Get it
quickly by following these steps:

1. **Download Appium Inspector:** Head over to the
   official [Appium Inspector GitHub releases page](https://github.com/appium/appium-inspector/releases).
   Download the latest version that is compatible with your operating system.
2. **Install Appium Inspector:** Open the downloaded file and drag the Appium
   Inspector app to your Applications folder.
3. **Grant Permissions** (if needed): Some downloaded applications
   might require permission to run on macOS. If you encounter this issue, open a
   terminal
   window and execute the following command:

    ```bash
    xattr -d com.apple.quarantine /Applications/Appium\ Inspector.app
    ```

4. **Run Appium server**: Open a terminal, go to the project's root folder, and
   run the following command:

    ```bash
    appium
    ```

5. **Connect Your Device:** Launch Appium Inspector. You'll need to add the
   desired capabilities to the initial screen to connect to a real or emulated
   device. Here's an example of an Android device:

    ```json
    {
        "appium:deviceName": "t650c",
        "appium:automationName": "UiAutomator2",
        "appium:platformName": "Android"
    }
    ```

---

## 📱 Connecting a Real Android Device

1. **Enable Developer Options on Your Device**:
    - Go to **Settings > About Phone**.
    - Tap **Build Number** 7 times until you see “You are now a developer.”

2. **Enable USB Debugging**:
    - Go to **Settings > Developer Options**.
    - Enable **USB Debugging**.
    - Optionally, enable **ADB over Network** if connecting via Wi-Fi.

3. **Connect Your Device via USB**:
    - Connect your Android device to your development machine using a USB cable.

4. **Connect Your Device via Wi-Fi**:
    - Ensure your Android device is connected to the same network as your
      development machine.
    - Find the IP address of your device: **Settings > About > Device Info**.
    - Open a terminal or command prompt and use the following command to connect
      to your device:

   ```bash
   adb connect <device-ip>:5037
   ```

5. **Verify Device Connection with ADB**:

   Open a terminal or command prompt and use the following command to list
   connected devices:

   ```bash
   adb devices
   ```

   The output should look similar to this:

   ```
   List of devices attached
   emulator-5554	device
   ```

---

## 🐞 Troubleshooting

### Error: Appium Cannot Locate the Device

If Appium cannot locate your device, it may have a name different from the one
specified in your code. To find the correct device name, run the following
command:

```bash
adb devices
```

The output will show something like:

```
List of devices attached
emulator-5554	device
```

In this example, the device name is `emulator-5554`. Ensure the device name in
your `/src/test/resources/serenity.conf` file matches this name.
