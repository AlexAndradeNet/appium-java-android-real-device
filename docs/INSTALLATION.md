# ANDROID INSTALLATION

We’ll need some dependencies to be preinstalled on your dev machine.

Let’s go over them one by one.

Also, remember it’s completely okay if you don’t understand all the details of
these in the beginning since Appium pretty much abstracts those details away,
and
you can always dig deeper later on if you need some very specific capabilities
of these libraries.

---

## ✏️ STEPS

### Installing Android Tools

These instructions are for Mac OS X. If you are using Windows, you can find the
instructions here: https://developer.android.com/studio/install
These instructions install the minimal tools needed to run the tests. If you
want to install the full Android Studio, you can find the instructions
here: https://developer.android.com/studio/install

```bash
brew update
brew install openjdk@17
brew install android-commandlinetools --cask
echo "export ANDROID_SDK_ROOT=/opt/homebrew/share/android-commandlinetools/" >> ~/.zprofile
source ~/.zprofile
```

If you are in Intel based Mac, you need change the `arm64-v8a` to `x86_64` in
the following command:

```bash
sdkmanager "build-tools;33.0.0" "platform-tools" "emulator" "system-images;android-33;google_apis;arm64-v8a" "platforms;android-33"

# verify everything got installed
sdkmanager --list_installed
avdmanager create avd -n "Pixel_5" -d "pixel_5" -k "system-images;android-33;google_apis;arm64-v8a"
emulator -list-avds
emulator -avd Pixel_5
```

### Install appium 2.x

If you installed Appium before with Homebrew, you might need to uninstall it.
Also, you might need to uninstall the appium-doctor and appium-desktop packages.

```bash
npm i -g appium@next
```

Install the webdriver

```bash
appium driver install uiautomator2
```

### Install Appium Inspector

Appium Inspector is a GUI application to help us inspect the elements of our app
retrieving the locators.

https://github.com/appium/appium-inspector/releases


---

## 📱 Connect a Real Android device

1. Enable Developer Options on Your Device:
    - Go to Settings > About Phone.
    - Tap Build Number 7 times until it says “You are now a developer.”
2. Enable USB Debugging:
    - Go to Settings > Developer Options.
    - Enable USB Debugging.
    - Enable ADB over network.
3. Connect Your Device via USB:
    - Connect your Android device to your development machine using a USB cable.
4. Connect Your Device via Wi-Fi:
    - Connect your Android device to the same network as your development
      machine.
    - Find the IP address of your device: Settings > About > Device Info.
    - Open a terminal or command prompt on your machine. Type the following
      command to connect to your device:
   ```bash
    adb connect <device-ip>:5037
    ```
4. Verify Device Connection with adb: Open a terminal or command prompt on your
   machine. Type the following command to list connected devices:
    ```bash
     adb devices
     ```

---

## 🐞 Troubleshooting

### Error: Appium cannot locate the device

The device can have a different name than the one you are using in the code. You
can check the name of the device by running the following command:

```bash
adb devices
```

The result is similar to this:

```
List of devices attached
emulator-5554	device
```

In this case, the device name is `emulator-5554`. You need to change the device
name in the file `/src/test/resources/serenity.conf`.
