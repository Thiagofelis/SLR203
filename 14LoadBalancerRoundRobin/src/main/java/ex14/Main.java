package ex14;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef ld = system.actorOf(LoadBalancer.createActor(), "ld");

    ActorRef a = system.actorOf(User.createActor(ld), "a");
    ActorRef b = system.actorOf(User.createActor(ld), "b");
    ActorRef c = system.actorOf(User.createActor(ld), "c");

    b.tell("join", ActorRef.noSender());
    c.tell("join", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    a.tell(new Task("m1"), ActorRef.noSender());
    a.tell(new Task("m2"), ActorRef.noSender());
    a.tell(new Task("m3"), ActorRef.noSender());
    a.tell(new Task("m4"), ActorRef.noSender());
    a.tell(new Task("m5"), ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    c.tell("unjoin", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    a.tell(new Task("m6"), ActorRef.noSender());
    a.tell(new Task("m7"), ActorRef.noSender());
    a.tell(new Task("m8"), ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
