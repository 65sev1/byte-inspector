Ревью 1
=====================

pom.xml
==============

Очень как-то не правильно составлен pom.xml
разбираюсь почему не компилируется

Косяки

1. Конечно оно не компилируется [ибо](https://github.com/65sev1/byte-inspector/blob/c03ca695c0ed99ec464ab5ab93762a9c50a8e336/pom.xml#L10) `<packaging>pom</packaging>`, а должно `<packaging>jar</packaging>` 
2. В двух местах указаны разные версии java
   1. в `build/plugins/plugin/configuration/source` - [16](https://github.com/65sev1/byte-inspector/blob/c03ca695c0ed99ec464ab5ab93762a9c50a8e336/pom.xml#L17) 
   2. в `build/plugins/plugin/configuration/target` - 16
   3. в `properties/maven.compiler.source` - [8](https://github.com/65sev1/byte-inspector/blob/c03ca695c0ed99ec464ab5ab93762a9c50a8e336/pom.xml#L25)
   4. в `properties/maven.compiler.target` - 8

Не указано

- Кодировка исходников
- версия плагина [maven-compiler-plugin](https://github.com/65sev1/byte-inspector/blob/f10e5d6c6a1e076dff752e60f90eb68408f1cf26/pom.xml#L15)
- licenses
- developers
- scm

.gitignore
================

Стоит внести в ignore каталоги

- `/target`
- `/.idea`

src
====================

* На 17 java скомпилировалось, но пришлось менять `toList` на `collect(Collectors.toList())`
