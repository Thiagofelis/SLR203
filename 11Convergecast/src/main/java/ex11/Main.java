package ex11;

import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");
    ActorRef d = system.actorOf(Receiver.createActor(), "d");
    ActorRef merger = system.actorOf(Merger.createActor(d), "merger");
    ActorRef a = system.actorOf(Sender.createActor(merger), "a");
    ActorRef b = system.actorOf(Sender.createActor(merger), "b");
    ActorRef c = system.actorOf(Sender.createActor(merger), "c");


    a.tell("join", ActorRef.noSender());
    b.tell("join", ActorRef.noSender());
    c.tell("join", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    a.tell("hi", ActorRef.noSender());
    b.tell("hi", ActorRef.noSender());
    c.tell("hi", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    c.tell("unjoin", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    b.tell("hi", ActorRef.noSender());
    c.tell("hi", ActorRef.noSender());
    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
