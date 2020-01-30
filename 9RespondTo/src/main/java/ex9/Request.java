package ex9;
import akka.actor.*;

public class Request
{
  Request (String m, ActorRef a) {message = m; respondTo = a;}
  String message;
  ActorRef respondTo;
  ActorRef getActorRef() {return respondTo;}
  String getMessage() {return message;}
}
