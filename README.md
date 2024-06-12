# Bananaconf

Bananaconf is a simple library for reading and writing plain text config files. The library itself is very small, providing you freedom to setup configurations as you wish.

**This repository is brand new and in early development.**

# Requirements
* Scala 3

# Example usecase

Imagine you have the following config, named "config.txt":
```
# This is a comment and will not be read
use_ffmpeg=yes
resolution=1920:1080
output_path=/path/to/file

file=video1.mov
file=video2.mov
file=funnymusic.flac
file=image.png
```

You can read the config and its properties this way:

```scala
import bananaconf.*

val settings = Vector("use_ffmpeg=", "resolution=", "output_path=")
val config_settings = readConfig("config.txt", settings, '#') //Reads the config into a Vector[String] containing the lines that have the accepted settings
// Lines that start with '#' are ignored

val use_ffmpeg = readFirstEntry(config_settings, "use_ffmpeg=") //Returns the string "yes"
val use_ffmpeg_bool = getVal_bool(use_ffmpeg) //Converts the "yes" to a "true" boolean

val resolution =
  getVal_parse(config_settings, "resolution=", ':') //Returns the Vector("1920", "1080")
  .map(x => getVal_int(x)) //Converts the strings to ints, returning a Vector[Int](1920, 1080)

val output_path = getVal(config_settings, "output_path=") //Gets the string "/path/to/file"

val media_paths = readEntries(config_settings, "file=") //Reads all entries for the file setting and stores them in a single vector
//The result would be a Vector[String]("video1.mov", "video2.mov", "funnymusic.flac", "image.png")
```

CPU and memory usage are directly proportional to the size of your configuration file and should raise linearly.

# Documentation

Documentation can be found in the following categories:

* #### [Config reader](doc/reader.md)
* #### [Config writer](doc/writer.md)
* #### [Misc](doc/misc.md)
