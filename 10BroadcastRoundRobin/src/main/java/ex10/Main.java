package ex10;

import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef broadcaster = system.actorOf(Broadcaster.createActor(), "broadcaster");
    ActorRef a = system.actorOf(A.createActor(broadcaster), "a");
    ActorRef b = system.actorOf(A.createActor(broadcaster), "b");
    ActorRef c = system.actorOf(A.createActor(broadcaster), "c");

    b.tell(broadcaster, ActorRef.noSender());
    c.tell(broadcaster, ActorRef.noSender());
    try{
      Thread.sleep(50);
    }catch (Exception e) {}
    a.tell("hi guys!", ActorRef.noSender());
    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
