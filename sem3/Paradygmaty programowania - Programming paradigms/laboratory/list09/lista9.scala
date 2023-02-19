//Kamil Ciaglo

//zadanie 1
class Rectangle(private val a:Double, private val b: Double):
  def this(a:Double) = this(a,a)
  def area: Double = a*b
  override def toString() = s"Rectangle: $a x $b, area: $area"

class UnderageException(message: String) extends Exception(message)
class NoNameException(message: String) extends Exception(message)
class NoSurnameException(message: String) extends Exception(message)

class Handyman(private val name: String, private val surname: String, private var age: Int):
  if(name == "") throw new NoNameException("Handyman must have a name")
  if(surname == "") throw new NoSurnameException("Handyman must have a surname")
  if(age < 18) throw new UnderageException("Handyman has to be 18 or older")
  override def toString() = s"$name $surname, aged: $age"

object Electrician:
  var amount = 0
  def howMany() = println(s"Number of electricians: ${Electrician.amount}")
trait Electrician:
  def fixElectricity() = println(s"Fixed your electricity!")
  Electrician.amount += 1

object Bricklayer:
  var amount = 0
  def howMany() = println(s"Number of bricklayers: ${Bricklayer.amount}")
trait Bricklayer:
  def layBricks() = println(s"Layed your bricks!")
  Bricklayer.amount += 1

object Painter:
  var amount = 0
  def howMany() = println(s"Number of painters: ${Painter.amount}")
trait Painter:
  def paintWalls() = println(s"Painted your walls!")
  Painter.amount += 1

object Main:
  def main(args: Array[String]):Unit =
    //-----z1-----
    ///*
    val rec = Rectangle(2,3)
    println(rec.toString())
    val rec2 = Rectangle(3)
    println(rec2.toString())
    //*/
    //-----z2-----
    ///*
    println()
    val JK = new Handyman("Jan", "Kowalski", 24) with Electrician with Bricklayer
    println(JK.toString())
    JK.fixElectricity()
    JK.layBricks()
    Electrician.howMany()
    Bricklayer.howMany()
    Painter.howMany()

    println()
    val AN = new Handyman("Alan", "Nowak", 23) with Painter with Bricklayer
    println(AN.toString())
    AN.paintWalls()
    Electrician.howMany()
    Bricklayer.howMany()
    Painter.howMany()

    println("\n")
    try
      val noname = new Handyman("", "Nowak", 23) with Painter with Bricklayer
    catch
      case e : Exception => println(e)

    println()
    try
      val nosurname = new Handyman("Alan", "", 23) with Painter with Bricklayer
    catch
      case e : Exception => println(e)

    println()
    try
      val underage = new Handyman("Alan", "Nowak", 5) with Painter with Bricklayer
    catch
      case e : Exception => println(e)
    println("\n")
    //*/
