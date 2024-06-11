# Bananaconf's misc functionality

Extra functions for filtering, sanitizing, processing values, etc. These functions are useful for interpreting and sanitizing configuration options to use them in other functions in your code.

---
```scala
def getVal_string(opt: String): String
```
If ```opt``` is "none", then this function returns an empty string, otherwise it returns ```opt```.


---
```scala
def getVal_int(opt: String): Int
```
```scala
def getVal_short(opt: String): Short
```
```scala
def getVal_byte(opt: String): Byte
```
If ```opt``` cannot be converted to a number, then this function returns 0, otherwise it returns ```opt``` as a number.

---
```scala
def getVal_boolean(opt: String): Boolean
```
If ```opt``` is "true" or "yes" (not case sensitive) then return true, otherwise false.

---
```scala
def fill_string(opt: Vector[String], max: Int): Vector[String]
```
```scala
def fill_int(opt: Vector[Int], max: Int): Vector[Int]
```
```scala
def fill_short(opt: Vector[Short], max: Int): Vector[Short]
```
```scala
def fill_byte(opt: Vector[Byte], max: Int): Vector[Byte]
```
If the vector ```opt``` has a length lower than ```max```, then the function fills the vector with empty strings/numbers to reach that length.
