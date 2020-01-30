package ex9;

import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef b = system.actorOf(B.createActor(), "b");
    ActorRef c = system.actorOf(C.createActor(), "c");

    ActorRef a = system.actorOf(A.createActor(b), "a");

    a.tell(new Request("req1", c), ActorRef.noSender());
    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
