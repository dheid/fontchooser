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

[![Linux/Mac Build Status](https://secure.travis-ci.org/dheid/fontchooser.png)](http://travis-ci.org/dheid/fontchooser)
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

Here is an example on how to use the font chooser dialog in your application:

```java
// better font rendering                                                                                    
System.setProperty("swing.aatext", "true");                                                                 
System.setProperty("awt.useSystemAAFontSettings", "lcd");                                                   
                                                                                                            
// FontChooserDialog and FontChooser provide different translations                                         
Locale.setDefault(new Locale("fi"));                                                                        
                                                                                                            
EventQueue.invokeAndWait(() -> {                                                                            
    FontChooserDialog dialog = new FontChooserDialog((Frame)null, "Font Dialog Example", true);             
    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);                                      
    dialog.setVisible(true);                                                                                
    if (!dialog.isCancelSelected()) {                                                                       
        System.out.println("Selected font is: " + dialog.getSelectedFont());                                
    }                                                                                                       
});                                                                                                         
```

You'll find the example in the file ```example/DialogExample.java```.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Credits

This is a modification of the JFontChooser component, written by Dr Christos Bohoris (Copyright 2009).

## License

GNU LESSER GENERAL PUBLIC LICENSE
Version 3, 29 June 2007
https://www.gnu.org/licenses/lgpl-3.0.en.html
