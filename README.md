# Font Chooser

This Java Swing component allows users to select a font by selecting a font family name, the style (plain, bold, italics)

There are translations for the following languages:

* English
* German
* Greek
* Spanish
* Finnish
* French
* Brazilian Portuguese
* Russian

## Continuous Integration

[![Build Status](https://travis-ci.org/dheid/fontchooser.svg?branch=master)](https://travis-ci.org/dheid/fontchooser)
[![Windows Build Status](https://img.shields.io/appveyor/ci/dheid/fontchooser/master.svg?label=windows)](https://ci.appveyor.com/project/dheid/fontchooser/branch/master)
[![Coverage Status](https://coveralls.io/repos/github/dheid/fontchooser/badge.svg?branch=master)](https://coveralls.io/github/dheid/fontchooser?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.dheid/fontchooser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.dheid/fontchooser/)

## Building

    mvn install

## Usage

Include the following dependency to your project:
```xml 
<dependency>
    <groupId>io.github.dheid</groupId>
    <artifactId>fontchooser</artifactId>
    <version>1.3</version>
 </dependency>
```

Here is a simple example on how to use the font chooser dialog in your application:

```java
FontChooserDialog dialog = new FontChooserDialog((Frame) null, "Font Dialog Example", true);
dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
dialog.setVisible(true);
if (!dialog.isCancelSelected()) {
  System.out.printf("Selected font is: %s%n", dialog.getSelectedFont());
}                                                               
```

You'll find more examples in the module fontchooser-example:

* [DialogExample](fontchooser-example/src/main/java/DialogExample.java)
* [PanelExample](fontchooser-example/src/main/java/PanelExample.java)

## Contributing

Any help is welcome. Especially translations are very useful. You can [open bug reports or feature requests](https://github.com/dheid/fontchooser/issues) or
implement new features.

1. [Fork it](https://github.com/dheid/fontchooser/fork)!
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
