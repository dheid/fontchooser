# Font Chooser

This Java Swing component allows users to select a font by selecting a font family name, the style (plain, bold, italics)

## Usage

        EventQueue.invokeAndWait(() -> {
            FontChooserDialog dialog = new FontChooserDialog((Frame) null, "Font Dialog Example", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (!dialog.isCancelSelected()) {
                System.out.println("Selected font is: " + dialog.getSelectedFont());
            }
        });

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