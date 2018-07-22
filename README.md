# Java Swing Font Chooser Dialog and Panel

Java does not provide a lightweight font chooser out of the box. This easy to use Java Swing font chooser component
allows users to select a font by selecting a font family name and the installed font variants. Users are able to search
for a font and insert their own preview text.

This font chooser is fully Java 18.9 compatible.

## Cross Platform
![Font Chooser (Cross Platform)](fontchooser-metallookandfeel.png "Font Chooser (Cross Platform)")

## GTK / Linux
![Font Chooser (GTK)](fontchooser-gtklookandfeel.png "Font Chooser (GTK)")

## Aqua / Mac
![Font Chooser (Aqua)](fontchooser-aqualookandfeel.png "Font Chooser (Aqua)")

## Windows
![Font Chooser (Windows)](fontchooser-windowslookandfeel.png "Font Chooser (Windows)")

## Motif / Common Desktop Environment
![Font Chooser (Motif)](fontchooser-motiflookandfeel.png "Font Chooser (Motif)")

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

## Continuous Integration

[![Build Status](https://travis-ci.org/dheid/fontchooser.svg?branch=master)](https://travis-ci.org/dheid/fontchooser)
[![Windows Build Status](https://img.shields.io/appveyor/ci/dheid/fontchooser/master.svg?label=windows)](https://ci.appveyor.com/project/dheid/fontchooser/branch/master)
[![Coverage Status](https://coveralls.io/repos/gitlab/dheid/fontchooser/badge.svg?branch=master)](https://coveralls.io/gitlab/dheid/fontchooser?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.dheid/fontchooser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.dheid/fontchooser/)

## Building

    mvn install

## Usage

Include the following dependency to your project:
```xml 
<dependency>
    <groupId>dr.jekyll</groupId>
    <artifactId>fontchooser</artifactId>
    <version>2.4</version>
 </dependency>
```

If you want to apply a font to a specific component, e.g. a text area, simply use

```java
JTextArea textArea = new JTextArea();
FontDialog.showDialog(textArea);
```

This will display the modal font dialog and apply the font to the component, if the user clicked OK.

Here is another simple example on how to use the font chooser dialog in your application:

```java
FontDialog dialog = new FontDialog((Frame) null, "Font Dialog Example", true);
dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
dialog.setVisible(true);
if (!dialog.isCancelSelected()) {
  System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
}                                                               
```

You'll find more examples in the module fontchooser-example:

* [DialogExample](fontchooser-example/src/main/java/DialogExample.java)
* [PanelExample](fontchooser-example/src/main/java/PanelExample.java)
* [ShowDialogExample](fontchooser-example/src/main/java/ShowDialogExample.java)

## Contributing

Any help is welcome. Especially translations are very useful. You can [open bug reports or feature requests](https://gitlab.com/dheid/fontchooser/issues) or
implement new features.

1. Fork it
2. Apply your changes and don't forget to add unit tests.
3. Build using Maven and ensure, that the build was successful.
3. Commit and push your changes.
4. Submit a [pull request](https://github.com/dheid/fontchooser/pulls) 

## Credits

This is a major rewrite of the JFontChooser component, originally written by Dr Christos Bohoris (Copyright 2009).

## License

GNU LESSER GENERAL PUBLIC LICENSE
Version 3, 29 June 2007
https://www.gnu.org/licenses/lgpl-3.0.en.html
