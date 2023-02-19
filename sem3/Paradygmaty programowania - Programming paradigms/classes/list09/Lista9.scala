//Kamil Ciaglo

//zadanie 1
class Time1(private var h: Int):
  require(h < 24, s"There are only 24 hours, not $h")
  if(h < 0) then h = 0
  else ()

  def hour: Int = h

  def hour_= (t: Int) =
    require(t < 24, s"There are only 24 hours, not $t")
    if(t < 0) then h = 0
    else h = t

  override def toString(): String = s"Time: $hour"

//zadanie 2
//a)
class Time2a(private var h: Int, private var m: Int):
  require(h < 24 && h >= 0, s"Invalid hour")
  require(m < 60 && m >= 0, s"Invalid minute")
  def hour: Int = h
  def hour_= (newHour: Int) =
    require(newHour < 24 && newHour >= 0, s"Invalid hour")
    if(newHour < 0) then h = 0
    else h = newHour
  def minute: Int = m
  def minute_= (newMinute: Int) =
    require(newMinute < 60 && newMinute >= 0, s"Invalid minute")
    if(newMinute < 0) then h = 0
    else h = newMinute
  def before(other: Time2a): Boolean =
    if(other.hour == h) then
      m < other.minute
    else
      h < other.hour
//b)
class Time2b(h: Int, m: Int):
  require(h < 24 && h >= 0, s"Invalid hour")
  require(m < 60 && m >= 0, s"Invalid minute")
  private var minutes: Int = (h * 60) + m

  def hour: Int = minutes/60
  def hour_= (newHour: Int) =
    require(newHour < 24 && newHour >= 0, s"Invalid hour")
    if(newHour < 0) then minutes = minute
    else minutes = newHour * 60 + minute

  def minute: Int = minutes%60
  def minute_= (newMinute: Int) =
    require(newMinute < 60 && newMinute >= 0, s"Invalid minute")
    if(newMinute < 0) then minutes = hour * 60
    else minutes = hour * 60 + newMinute

  def before(other: Time2b): Boolean =
    minutes < other.minutes

//zadanie 3
class Pojazd(val producent: String, val model: String, val rok: Int, var numer: String):
  def this(p:String, m:String) = this(p, m, -1, "")
  def this(p:String, m:String, n:String) = this(p, m, -1, n)
  def this(p:String, m:String, r:Int) = this(p, m, r, "")
  override def toString(): String = s"producent: $producent, model: $model, rok produkcji: $rok, numer rejestracyjny: $numer"

//zadanie 4
object UzycieWyjatkow:
  def main(/*args: Array[String]*/): Unit =
    try
      metoda1()
    catch
      case e: Exception => println(e)

  def metoda1() =
    metoda2()

  def metoda2() =
    metoda3()

  def metoda3() =
    throw new Exception("Wyjatek zgloszony w metoda3")

//testy
object Main:
  def main(args: Array[String]):Unit =
    //z1()
    //z2()
    //z3()
    z4()
    ()
  def z1() =
    val t1 = new Time1(-20)
    println(t1.toString())
    t1.hour = 23
    println(t1.toString())
    try
      t1.hour = 25
    catch
      case e: Exception => println(e)
    ()
  def z2() =
    val a = new Time2a(18,30)
    val a2 = new Time2a(19,10)
    println(a.before(a2))
    a.hour = 19
    println(a.before(a2))
    a.minute = 5
    println(a.before(a2))
    println()
    val b = new Time2b(18,30)
    val b2 = new Time2b(19,10)
    println(b.before(b2))
    b.hour = 19
    println(b.before(b2))
    b.minute = 5
    println(b.before(b2))
    println()
    ()
  def z3() =
    val p1 = new Pojazd("Producent", "Model")
    val p2 = new Pojazd("Producent", "Model", 1234)
    val p3 = new Pojazd("Producent", "Model", "Numer")
    val p4 = new Pojazd("Producent", "Model", 1234, "Numer")
    println(p1.toString())
    println(p2.toString())
    println(p3.toString())
    println(p4.toString())
    ()
  def z4() =
    UzycieWyjatkow.main()
    ()