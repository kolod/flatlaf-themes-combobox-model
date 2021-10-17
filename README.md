# FlatlafThemesComboBoxModel
![GitHub](https://img.shields.io/github/license/kolod/flatlaf-themes-combobox-model?style=flat-square)[![javadoc](https://javadoc.io/badge2/io.github.kolod/flatlaf-themes-combobox-model/javadoc.svg?style=flat-square&color=97CA00)](https://javadoc.io/doc/io.github.kolod/flatlaf-themes-combobox-model)[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.kolod/flatlaf-themes-combobox-model/badge.svg?style=flat-square&color=97CA00)](https://maven-badges.herokuapp.com/maven-central/io.github.kolod/flatlaf-themes-combobox-model)

The ComboBox model for the [FlatLaf](https://github.com/JFormDesigner/FlatLaf) themes.

![ComboBox screenshot](combobox.png)

## Download
FlatlafThemesComboBoxModel binaries are available on **Maven Central**.

If you use Maven or Gradle, add a dependency with following coordinates to your
build script:

```yaml
    groupId:     com.formdev
    artifactId:  flatlaf
    version:     (see button below)
```
Otherwise download  `flatlaf-themes-combobox-model-<version>.jar` here:

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.kolod/flatlaf-themes-combobox-model/badge.svg?style=for-the-badge&color=97CA00)](https://maven-badges.herokuapp.com/maven-central/io.github.kolod/flatlaf-themes-combobox-model)

## Usage

Place the `JComboBox` component on your form and then set the model to an instance of the `FlatlafThemesComboBoxModel`.

```java
private static FlatlafThemesComboBoxModel themesModel;
private javax.swing.JComboBox<String> themesComboBox;
...
themesModel = new FlatlafThemesComboBoxModel();
themesComboBox = new javax.swing.JComboBox<>();
themesComboBox.setModel(themesModel);
```
