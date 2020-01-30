package ex18;
import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef creator = system.actorOf(Creator.createActor(), "creator");

    creator.tell("create", ActorRef.noSender());
    creator.tell("create", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    ActorSelection a = system.actorSelection("/user/actor");
    ActorSelection a1 = system.actorSelection("/user/actor/actor1");
    ActorSelection a2 = system.actorSelection("/user/actor/actor2");

    System.out.println("a: " + a.path());
    System.out.println("a1: " + a1.path());
    System.out.println("a2: " + a2.path());

    

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
