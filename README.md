# Appium Tests

This project contains tests written with the help of Gherkin + Cucumber, which run on Appium server on an Android device.

### Prerequisites

In order to successfully run these tests, you should have the following Plugins

```
Gherkin
Cucumber for Java
```

Android SDK has to be installed on your machine and environment variable set
```
ANDROID_HOME
```

You also need to have the desired application installed on your test device, 
which in our case is the Printful app downloaded from Google Play Store:
[Printful](https://play.google.com/store/apps/details?id=com.printful.app) - The web framework used

## Running the tests

Tests can be ran directly from .feature files located in folder: *src -> test -> resources -> features.* \
The Appium server will run on any available port automatically.

## Setting up device

On your test device, make sure that Developer options are enabled ([Configure on-device developer options](https://developer.android.com/studio/debug/dev-options)) \
as well as USB debugging is enabled under the "DEBUGGING" section.

## Adding device device capabilities
Device properties should be added in the *configuration.properties* file located: *src -> test -> resources -> configuration.properties*.
```
android.device.name has to set to deviceId, that can be checked by
running command: adb devices in Terminal while your device is connected to
your machine via USB.
```

## Change the testing app
Since there's no .apk file located in this project, this projects counts on the desired app to be
installed on your device. \
This can easily be updated by changing parameters in *configuration.properties*:
```
android.app.packageName
android.app.activityName
```
Which can most easily be checked via [Apk Info app](https://play.google.com/store/apps/details?id=com.wt.apkinfo) \
Or if possible, ask your developers, because idealistically an .apk file should be kept in project
and be installed on testing device once running tests.

### Adding new steps

Adding new custom steps is really straight forward, and can be done with the following steps:
1. write the desired step in .feature file,
2. "Undefined step reference" should be displayed for step,
3. use shortcut: ALT + ENTER
4. select "Create step definition"
5. choose definition file
6. write desired logic for generated step

## Authors

* **Artis Mocans** -  [GitHub](https://github.com/artisMocans)