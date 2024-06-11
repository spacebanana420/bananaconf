package test

import bananaconf.*

@main def main() =
  createConfig("config.txt")
  val conf = "setting1=test\nsetting2=test:3:5\nsetting2=test:4:2"
  writeConfig_string("config.txt", conf)
  val settings = Vector("setting1=", "setting2=", "setting3=")
  val conf_read = readConfig("config.txt", settings, '#')

  for s <- readEntries(conf_read, "setting1=") do println(s)
  for s <- readEntries(conf_read, "setting2=") do println(s)
  println(readFirstEntry(conf_read, "setting2="))
