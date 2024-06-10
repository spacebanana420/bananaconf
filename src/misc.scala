package bananaconf

def getVal_string(opt: String): String =
  if opt != "none" then opt else ""

def getVal_int(opt: String): Int =
  try opt.toInt
  catch case e: Exception => 0

def getVal_short(opt: String): Short =
  try opt.toShort
  catch case e: Exception => 0

def getVal_boolean(opt: String): Boolean =
  val l_opt = opt.toLowerCase()
  l_opt == "true" || l_opt == "yes"

def fill_string(opt: Vector[String], max: Int): Vector[String] =
  if opt.length >= max then opt
  else fill_string(opt :+ "", max)

def fill_int(opt: Vector[Int], max: Int): Vector[Int] =
  if opt.length >= max then opt
  else fill_int(opt :+ 0, max)

def fill_short(opt: Vector[Short], max: Int): Vector[Short] =
  if opt.length >= max then opt
  else fill_short(opt :+ 0, max)

