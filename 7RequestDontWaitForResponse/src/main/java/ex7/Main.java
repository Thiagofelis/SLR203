package ex7;

import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef a = system.actorOf(A.createActor(), "a");
    ActorRef b = system.actorOf(B.createActor(), "b");

    a.tell(b, ActorRef.noSender());

    a.tell("req1", ActorRef.noSender());
    try{
      Thread.sleep(10);
    } catch (Exception e) {}
    a.tell("req2", ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();

  }
}
