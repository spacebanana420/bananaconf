package bananaconf

import java.io.File
import java.io.FileOutputStream

def createConfig(path: String) =
  val pathig = FileOutputStream(path)

def writeConfig(path: String, cfg: Seq[String], append: Boolean = true) =
  def createStr(str: String = "", i: Int = 0): String =
    if i >= cfg.length then
      str
    else if cfg(i) == "" then
      createStr(str, i+1)
    else
      createStr(str + cfg(i) + '\n', i+1)

  val cfgstr = createStr()
  if cfgstr != "" then
    FileOutputStream(path, append).write(cfgstr.getBytes())

def writeConfig_string(path: String, cfg: String, append: Boolean = true) =
  if cfg != "" then
    FileOutputStream(path, append).write(cfg.getBytes())

def writeConfig_bytes(path: String, cfg: Array[Byte], append: Boolean = true) =
  if cfg.length > 0 then
    FileOutputStream(path, append).write(cfg)
