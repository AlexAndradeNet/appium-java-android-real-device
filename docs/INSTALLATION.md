# ANDROID INSTALLATION

Before running Android tests with Appium, some dependencies need to be
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
> the [Android Studio setup guide](https://developer.android.com/studio/install).

1. **Install OpenJDK and Android Command Line Tools**

   Open a terminal and run the following commands to update Homebrew and install
   the necessary dependencies:

   ```bash
   brew update
   brew install openjdk@17
   brew install android-commandlinetools --cask
   ```

2. **Set Up Environment Variables**

   Define the `ANDROID_SDK_ROOT` environment variable by adding the following
   line to your `~/.zprofile`:

   ```bash
   echo "export ANDROID_SDK_ROOT=/opt/homebrew/share/android-commandlinetools/" >> ~/.zprofile
   source ~/.zprofile
   ```

3. **Install Android SDK Components**

   Install the essential Android SDK components using `sdkmanager`. If you are
   using an Intel-based Mac, replace `arm64-v8a` with `x86_64` in the command
   below:

   ```bash
   sdkmanager "build-tools;33.0.0" "platform-tools" "emulator" "system-images;android-33;google_apis;arm64-v8a" "platforms;android-33"
   ```

4. **Verify Installation**

   Run the following commands to verify your installation:

   ```bash
   # Verify installed components
   sdkmanager --list_installed
    ```

### Installing Appium 2.x

If you previously installed Appium using Homebrew, uninstall it to avoid
conflicts. Additionally, remove any `appium-doctor` or `appium-desktop`
packages.

1. **Install Appium**

   Install the latest version of Appium globally using `npm`:

   ```bash
   npm i -g appium@next
   ```

2. **Install the Required WebDriver**

   After installing Appium, install the required WebDriver for Android:

   ```bash
   appium driver install uiautomator2
   ```

### Installing Appium Inspector

Appium Inspector is a GUI tool that helps inspect the elements of your app and
retrieve their locators. You can download it from
the [Appium Inspector GitHub releases page](https://github.com/appium/appium-inspector/releases).

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

If Appium cannot locate your device, it may have a different name than specified
in your code. To find the correct device name, run the following command:

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
