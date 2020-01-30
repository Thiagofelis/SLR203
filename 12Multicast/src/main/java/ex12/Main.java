package ex12;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef multicaster = system.actorOf(Multicaster.createActor(), "multicaster");
    ActorRef sender = system.actorOf(Sender.createActor(multicaster), "sender");
    ActorRef receiver1 = system.actorOf(Receiver.createActor(), "receiver1");
    ActorRef receiver2 = system.actorOf(Receiver.createActor(), "receiver2");
    ActorRef receiver3 = system.actorOf(Receiver.createActor(), "receiver3");

    List<ActorRef> group1 = new ArrayList<ActorRef>();
    group1.add(receiver1);
    group1.add(receiver2);
    List<ActorRef> group2 = new ArrayList<ActorRef>();
    group2.add(receiver2);
    group2.add(receiver3);

    sender.tell(new Group("group1", group1), ActorRef.noSender());
    sender.tell(new Group("group2", group2), ActorRef.noSender());

    sender.tell(new Message("group1", "Hello"), ActorRef.noSender());
    sender.tell(new Message("group2", "World"), ActorRef.noSender());

    sender.tell(new Message("group3", "dont receiver"), ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
