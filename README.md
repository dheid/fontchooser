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

## Building

    mvn package

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
