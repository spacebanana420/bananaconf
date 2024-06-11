# Bananaconf's config writer

The following functions are associated to creating config files and writing to them

---

```scala
def createConfig(path: String)
```
Creates an empty file at the path ```path```.

---

```scala
def writeConfig(path: String, cfg: Seq[String], append: Boolean = true)
```
```scala
def writeConfig_string(path: String, cfg: String, append: Boolean = true)
```
```scala
def writeConfig_bytes(path: String, cfg: Array[Byte], append: Boolean = true)
```
Writes the contents of ```cfg``` into the file in ```path```. If no file exists, then one is created. If ```append``` is set to true, if a file exists prior to writing to it, it will not be overwritten.
