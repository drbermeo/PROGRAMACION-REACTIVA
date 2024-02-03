/* Información del Código:
Es un programa simple en el cual el Emisor envía números al Observador, y al
final del flujo, el Observador imprime un mensaje y termina el sistema de actores*/

// Importación de Paquetes
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

// Definición de Mensajes
case class NuevoValor(value: Int)
case object FinFlujo
case object EmitirNumeros

// Actor Observador
class Observador extends Actor {
  def receive: Receive = {
    case NuevoValor(value) =>
      println(s"Observador: Nuevo valor recibido: $value")
    case FinFlujo =>
      println("Observador: Fin del flujo")
      context.system.terminate()
  }
}

// Actor Emisor
class Emisor(observer: ActorRef) extends Actor {
  def receive: Receive = {
    case EmitirNumeros =>
      val numbers = 3 to 9
      numbers.foreach(observer ! NuevoValor(_))
      observer ! FinFlujo
  }
}
//OBJETO PRINCIPAL
object reactive {

    def main(args: Array[String]): Unit = {
      // Crear el sistema de actores
      val system = ActorSystem("ReactiveSystem")
      // Crear el actor observador
      val observador = system.actorOf(Props(classOf[Observador]), "observador")
      // Crear el actor emisor y pasarle el observador como referencia
      val emisor = system.actorOf(Props(new Emisor(observador)), "emisor")
      // Iniciar el flujo de eventos
      emisor ! EmitirNumeros
    }
}
