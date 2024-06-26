# Bananaconf's config reader

The following functions are associated to reading config files

---

```scala
def configExists(conf: String): Boolean
```
Returns a boolean value depending on whether a file at the path ```conf``` exists.

---

```scala
def readConfig(conf: String, settings: Seq[String] = Vector(), comment_char: Char): Vector[String]
```
Reads the file in the path ```conf``` and returns a vector, with each string representing a line.

```settings``` filters lines that do not start with the keywords in this sequence. You can disable this filter by using the default value or passing an empty sequence.

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
readConfig("config.txt", '#', Vector("setting1=", "setting2="))
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
through ```getValue()``` this waty:

```scala
getValue("setting1=abc:4:kogger", "setting1=")
```

we get the string ```"abc:4:kogger"```.

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

---

```scala
def getValue_parse(line: String, setting: String, separator: Char): Vector[String]
```
Reads the value from a setting entry and parses it. This function is a convenient combination of ```getValue()``` and ```parseEntry()```.

```scala
val setting_line = "option=doremy:1920:1080:run before it's too late"
getValue_parse(setting_line, "option=", ':')
```
The following function call would return the vector
```scala
Vector("doremy", "1920", "1080", "run before it's too late")
```
