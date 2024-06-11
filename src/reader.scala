package bananaconf

import java.io.File
import java.io.FileOutputStream
import scala.io.Source


def configExists(conf: String): Boolean = File(conf).isFile()

private def isSetting(line: String, keywords: Seq[String], i: Int = 0): Boolean =
  def startsWith(key: String, c: Int = 0): Boolean =
    if c >= key.length then true
    else if line.length <= key.length || line(c) != key(c) then false //excludes empty configurations too
    else startsWith(key, c+1)

  if i >= keywords.length then false
  else if startsWith(keywords(i)) then true
  else isSetting(line, keywords, i+1)

def readConfig(conf: String, settings: Seq[String], comment_char: Char): Vector[String] =
  val src = Source.fromFile(conf)
  val cfg = src
    .getLines()
    .filter(x => x.length > 0 && isSetting(x, settings) && x(0) != comment_char)
    .toVector
  src.close()
  cfg

def readConfig_raw(conf: String): Vector[String] =
  val src = Source.fromFile(conf)
  val cfg = src
    .getLines()
    .filter(x => x.length > 0)
    .toVector
  src.close()
  cfg

def filterEntries(conf: Vector[String], setting: String): Vector[String] =
  conf.filter(x => isSetting(x, Vector(setting)))

def getValue(l: String, setting: String, tmp: String = "", value: String = "", i: Int = 0): String =
  if i >= l.length || (i >= setting.length && setting != tmp) then
    value
  else if tmp == setting then
    getValue(l, setting, tmp, value + l(i), i+1)
  else
    getValue(l, setting, tmp + l(i), value, i+1)

// def parseEntry(entry: String, separator: Char, e1: String = "", e2: String = "", i: Int = 0, first: Boolean = true): Vector[String] = //replace with an infinite size vector!!!!!!!!!!!
//   if i >= entry.length then
//     Vector(e1, e2)
//   else if entry(i) == separator && first then
//     parseEntry(entry, separator, e1, e2, i+1, false)
//   else if first then
//     parseEntry(entry, separator, e1 + entry(i), e2, i+1, first)
//   else
//     parseEntry(entry, separator, e1, e2 + entry(i), i+1, first)

def parseEntry(entry: String, separator: Char, value: String = "", values: Vector[String] = Vector(), i: Int = 0): Vector[String] =
  if i >= entry.length then
    if value == "" then values else values :+ value
  else if entry(i) == separator then
    parseEntry(entry, separator, "", values :+ value, i+1)
  else
    parseEntry(entry, separator, value + entry(i), values, i+1)


def readEntries(cfg: Seq[String], setting: String, vals: Vector[String] = Vector(), i: Int = 0): Vector[String] =
  if i >= cfg.length then
    vals
  else
    val value = getValue(cfg(i), setting)
    if value != "" then
      readEntries(cfg, setting, vals :+ value, i+1)
    else
      readEntries(cfg, setting, vals, i+1)

def readFirstEntry(cfg: Seq[String], setting: String, i: Int = 0): String =
  if i >= cfg.length then
    ""
  else
    val value = getValue(cfg(i), setting)
    if value != "" then
      value
    else
      readFirstEntry(cfg, setting, i+1)

def parseFirstEntry(cfg: Seq[String], separator: Char, setting: String, i: Int = 0): Vector[String] =
  parseEntry(readFirstEntry(cfg, setting, i), separator)

def getValue_parse(line: String, setting: String, separator: Char): Vector[String] = parseEntry(getValue(line, setting), separator)
