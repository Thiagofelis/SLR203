package ex2;
import akka.event.*;
import akka.actor.*;

public class Main
{
  public static void main(String[] args)
  {
    final ActorSystem system = ActorSystem.create("System");
    final ActorRef a1 = system.actorOf(A1.createActor(), "a1");
    final ActorRef a2 = system.actorOf(A2.createActor(), "a2");

  }
}props
