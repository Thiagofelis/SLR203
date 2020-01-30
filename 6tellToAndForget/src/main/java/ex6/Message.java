package ex6;

import akka.actor.*;

public class Message
{
  ActorRef target;
  String message;
  Message(ActorRef t, String m) {target = t; message = m;}

  ActorRef getTarget() {return target;}
  String getMessage() {return message;}
}
