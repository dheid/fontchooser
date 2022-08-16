![Java Swing Font Chooser Logo](logo.svg)

# :pencil2: Java Swing Font Chooser Dialog and Panel

[![Maven Central](https://img.shields.io/maven-central/v/org.drjekyll/fontchooser.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22org.drjekyll%22%20AND%20a:%22fontchooser%22)
[![Java CI with Maven](https://github.com/dheid/fontchooser/actions/workflows/build.yml/badge.svg)](https://github.com/dheid/fontchooser/actions/workflows/build.yml)
[![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/W7W3EER56)

Java does not provide a lightweight font chooser out of the box. This easy to use Java Swing font chooser component
allows users to select a font by selecting a font family name and the installed font variants. Users are able to search
for a font and insert their own preview text.

* Compact design
* Looks good on every operating system
* Allows to find a font family easily
* Supports font styles
* Shows font in preview
* Allows to change the preview text
* Compatible with Java 8 and upwards
* Easy to use
* Well documented with Javadoc
* No dependencies

## :dancers: Cross Platform
![Font Chooser (Cross Platform)](fontchooser-metallookandfeel.png "Font Chooser (Cross Platform)")

## :penguin: GTK / Linux
![Font Chooser (GTK)](fontchooser-gtklookandfeel.png "Font Chooser (GTK)")

## :apple: Aqua / Mac
![Font Chooser (Aqua)](fontchooser-aqualookandfeel.png "Font Chooser (Aqua)")

## :office: Windows
![Font Chooser (Windows)](fontchooser-windowslookandfeel.png "Font Chooser (Windows)")

There are translations for the following languages:

* English
* German
* Greek
* Hungarian
* Spanish
* Finnish
* French
* Brazilian Portuguese
* Russian

Font Chooser is an open source project and completely free. I appreciate improvements or extensions. Please contact
me if you have questions.

## :wrench: Usage

Include the following dependency to your project:

```xml 
<dependency>
    <groupId>org.drjekyll</groupId>
    <artifactId>fontchooser</artifactId>
    <version>2.5.1</version>
 </dependency>
```

or Gradle with Groovy DSL:

```groovy
implementation 'org.drjekyll:fontchooser:2.5.1'
```

or Gradle with Kotlin DSL:

```kotlin
implementation("org.drjekyll:fontchooser:2.5.1")
```

If you want to apply a font to a specific component, e.g. a text area, simply use

```java
JTextArea textArea = new JTextArea();
FontDialog.showDialog(textArea);
```

This will display the modal font dialog and apply the font to the component, if the user clicked OK.

Here is another simple example on how to use the font chooser dialog in your application:

```java
FontDialog dialog = new FontDialog((Frame)null,"Font Dialog Example",true);
dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
dialog.setVisible(true);
if(!dialog.isCancelSelected()){
  System.out.printf("Selected font is: %s%n",dialog.getSelectedFont());
}                                                               
```

You'll find more examples in the module fontchooser-example:

* [DialogExample](examples/DialogExample.java)
* [PanelExample](examples/PanelExample.java)
* [ShowDialogExample](examples/ShowDialogExample.java)

## :hammer: Building

Please use Maven to build and test the project.

    mvn install

This will install the version to your local repository. You can now include it:

```xml 
<dependency>
    <groupId>org.drjekyll</groupId>
    <artifactId>fontchooser</artifactId>
    <version>2.5.2-SNAPSHOT</version>
 </dependency>
```

or Gradle with Groovy DSL:

```groovy
implementation 'org.drjekyll:fontchooser:2.5.1-SNAPSHOT'
```

or Gradle with Kotlin DSL:

```kotlin
implementation("org.drjekyll:fontchooser:2.5.1-SNAPSHOT")
```

## :handshake: Contributing and Credits

Please see here: [Contributing](CONTRIBUTING.md)

This is a major rewrite of the JFontChooser component, originally written by Dr Christos Bohoris (Copyright 2009).

## :notebook: Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see
the [tags on this repository](https://github.com/dheid/friendlycaptcha/tags).

## :scroll: License

GNU LESSER GENERAL PUBLIC LICENSE
Version 3, 29 June 2007
https://www.gnu.org/licenses/lgpl-3.0.en.html

## :loudspeaker: Release Notes

### 2.5.1

* Introduced Lombok
* Migrated to JUnit 5
* Updated dependencies and plugins
* Migrated to GitHub
