# Bananaconf's config reader

The following functions are associated to reading config files

---

```scala
def configExists(conf: String): Boolean
```
Returns a boolean value depending on whether a file at the path ```conf``` exists.

---

```scala
def readConfig(conf: String, settings: Seq[String], comment_char: Char): Vector[String]
```
Reads the file in the path ```conf``` and returns a vector, with each string representing a line.

```settings``` filters lines that do not start with its keywords.

Lines whose first character is ```comment_char``` are ignored.

Example of a configuration and its reading:

```
///Config example///
setting1=4
setting2=kogasa
setting3=doremy
#test123
```

```scala
readConfig("config.txt", Vector("setting1=", "setting2="), '#')
```
This function call would return the following vector:
```scala
Vector("setting1=4", "setting2=kogasa")
```
---

```scala
def readConfig_raw(conf: String): Vector[String]
```
Reads the file in ```conf``` without any filtering. Each element of the vector represents a line.

---
```scala
def filterEntries(conf: Vector[String], setting: String): Vector[String]
```
Returns a vector containing only the config lines that start with ```setting```.

---
```scala
def getValue(l: String, setting: String, tmp: String = "", value: String = "", i: Int = 0): String
```
Gets the value of a given setting line. It's not necessary to use this function directly unless you want to have finer control.

By passing the setting
```setting1=abc:4:kogger```
through ```getValue()```, we get the string ```abc:4:kogger```.

---
```scala
def parseEntry(entry: String, separator: Char, value: String = "", values: Vector[String] = Vector(), i: Int = 0): Vector[String]
```
Parses the value of a single setting ```entry``` into a vector. Values are separateed by the ```separator``` character.
```entry``` must be sanitized first by being passed through ```getValue()```

By passing the setting
```setting1=abc:4:kogger```
through ```getValue()```, we get the string ```abc:4:kogger```. We can now use this string in ```parseEntry```.

Assuming the following code:

```scala
val parameters = "abc:4:kogger"
val params_parsed = parseEntry(parameters, ':')
```

```params_parsed``` would be the string vector

```scala
Vector("abc", "4", "kogger")
```

---

```scala
def readEntries(cfg: Seq[String], setting: String, vals: Vector[String] = Vector(), i: Int = 0): Vector[String]
```
Reads the config settings from ```cfg``` and obtains the values of the lines representing the setting ```setting```. Essentially, it works as a combination of ```filterEntries()``` and sanitizing each entry with ```getValue()```.

---

```scala
def readFirstEntry(cfg: Seq[String], setting: String, i: Int = 0): String
```
Similar to ```readEntries()```, but only returns the first setting it finds.

---

```scala
def parseFirstEntry(cfg: Seq[String], separator: Char, setting: String, i: Int = 0): Vector[String]
```
Parses an obtained entry value, essentially working as a combination of ```parseEntry()``` with ```readFirstEntry()```
