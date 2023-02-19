//Kamil Ciaglo

//zadanie 4
import scala.collection.mutable.Seq
def copy[T](dest: Seq[T], src: Seq[T]): Unit =
  require(dest.length >= src.length, "Destination shorter than source")
  var i = 0
  src.foreach(e =>
    dest.update(i, e)
    i += 1)

