//Kamil Ciaglo

//zadanie 1
//skrotowo odnosze sie potem w komentarzach do klas jako kolejno: P, C, GC
class ParentClass //P
class ChildrenClass extends ParentClass //C extends P
class GrandChildrenClass extends ChildrenClass //GC extends C

def z1() =
  var PList = List[ParentClass]()
  var CList = List[ChildrenClass]()
  var GCList = List[GrandChildrenClass]()

  //ParentClass >: ChildrenClass >: GrandChildrenClass
  //CList = PList
  //nie mozemy wykonac przypisania List[P] do List[C] (!(List[P] <: List[C])), a P >: C, zatem List nie jest kontrawariantna
  CList = GCList
  //przypisanie List[GC] do List[C] jest możliwe (List[GC] <: List[C]), a GC <: C, zatem List jest kowariantna
  //skoro jest kowariantna, nie moze byc inwariantna, bo "Brak informacji o wariantności oznacza, że typ generyczny jest
  //inwariantny względem rozważanego parametru typowego."


//zadanie 2
import scala.collection.mutable.Queue

class TopSecretMessage(private val message: String):
  override def toString(): String = s"Top secret message \"$message\""
class EncryptedMessage(message: String) extends TopSecretMessage(message):
  override def toString(): String = s"Encrypted message \"$message\""
class PlainTextMessage(message: String) extends EncryptedMessage(message):
  override def toString(): String = s"Plain text message \"$message\""


trait Sender[-M]:
  def send(msg: M): Unit
trait Receiver[+M]:
  def receive(): Unit


class Channel[M] extends Sender[M] with Receiver[M]:
  private val Q = new Queue[M]()

  override def send(message: M): Unit =
    println(s"Sending: $message")
    Q.enqueue(message)

  override def receive(): Unit =
    if !(Q.isEmpty) then
      val message = Q.dequeue()
      println(s"Received: $message")
    else
      println(s"Channel is empty")

//testy
object Main:
  def main(args: Array[String]): Unit =
    z1()
    z2()
    ()
  def z2() =
    val PTM = new PlainTextMessage("PTM")
    val EM = new EncryptedMessage("EM")
    val TSM = new TopSecretMessage("TSM")

    val chp = new Channel[PlainTextMessage]()
    val che = new Channel[EncryptedMessage]()
    val cht = new Channel[TopSecretMessage]()

    //======================================
    //  "PlainTextMessage" security level
    //======================================
    println("\n\"PlainTextMessage\" security level\n")
    chp.send(PTM)
    //chp.send(EM)
    //chp.send(TSM)
    chp.receive()
    chp.receive()
    chp.receive()
    //======================================
    //  "EncryptedMessage" security level
    //======================================
    println("\n\"EncryptedMessage\" security level\n")
    che.send(PTM)
    che.send(EM)
    //che.send(TSM)
    che.receive()
    che.receive()
    che.receive()
    //======================================
    //  "TopSecretMessage" security level
    //======================================
    println("\n\"TopSecretMessage\" security level\n")
    cht.send(PTM)
    cht.send(EM)
    cht.send(TSM)
    cht.receive()
    cht.receive()
    cht.receive()

    //===============================================
    //  sending messages through different channels
    //===============================================
    println("\nSending messages through different channels")
    println("\nSender:")
    //"Na potrzeby wysyłania wiadomości kanał o wyższym stopniu bezpieczeństwa może
    //zostać użyty w miejscu kanału o niższym stopniu bezpieczeństwa"
    val chSend1: Sender[PlainTextMessage] = cht
    val chSend2: Sender[EncryptedMessage] = cht
    chSend1.send(PTM)
    //chSend1.send(EM)
    //chSend1.send(TSM)
    chSend2.send(PTM)
    chSend2.send(EM)
    //chSend2.send(TSM)
    //wszystkie powyzsze wiadomosci z kanalow Sender przejda nie przez kanal sam w sobie,
    // a przez kanal cht uzyty w jego miejscu, oto dowod
    cht.receive()
    cht.receive()
    cht.receive()
    cht.receive()

    //==================================================
    //  receiving messages through different channels
    //==================================================
    println("\nReceiver:")
    //"Na potrzeby odczytywania wiadomości kanał o niższym stopniu bezpieczeństwa może
    //zostać użyty w miejscu kanału o wyższym stopniu bezpieczeństwa"
    var chReceive: Receiver[TopSecretMessage] = che
    //na potrzeby demonstracji najpierw zapelniam kanaly chp i che wiadomosciami
    chp.send(PTM)
    che.send(PTM)
    che.send(EM)
    //kanal chReceie odbiera wiadomosci z uzyciem kanalu che, wiec receive odbierze wiadomosci z tego wlasnie kanalu
    chReceive.receive()
    chReceive.receive()
    chReceive.receive()
    //teraz zmienmy kanal i z kanalu chReceive odbierajmy wiadomosci z uzyciem chp
    println("\nChanging channel used by receiver:")
    chReceive = chp
    chReceive.receive()
    chReceive.receive()
