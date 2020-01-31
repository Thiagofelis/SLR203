package ex13;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef topic1 = system.actorOf(Topic.createActor("publisher1"), "topic1");
    ActorRef topic2 = system.actorOf(Topic.createActor("publisher2"), "topic2");

    ActorRef publisher1 = system.actorOf(Publisher.createActor(topic1), "publisher1");
    ActorRef publisher2 = system.actorOf(Publisher.createActor(topic2), "publisher2");

    ActorRef a = system.actorOf(Subscriber.createActor(topic1, topic2), "a");
    ActorRef b = system.actorOf(Subscriber.createActor(topic1, topic2), "b");
    ActorRef c = system.actorOf(Subscriber.createActor(topic1, topic2), "c");

    a.tell(new SubscriptionRequest("topic1"), ActorRef.noSender());
    b.tell(new SubscriptionRequest("topic1"), ActorRef.noSender());
    b.tell(new SubscriptionRequest("topic2"), ActorRef.noSender());
    c.tell(new SubscriptionRequest("topic2"), ActorRef.noSender());
    
    try{
      Thread.sleep(50);
    }catch (Exception e) {}
    
    publisher1.tell("hello", ActorRef.noSender());
    publisher2.tell("world", ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    a.tell(new UnsubscriptionRequest("topic1"), ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    publisher1.tell("hello2", ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
