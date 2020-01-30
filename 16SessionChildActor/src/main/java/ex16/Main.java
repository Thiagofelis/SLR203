package ex16;
import akka.actor.*;
import akka.event.*;

public class Main
{
  public static void main(String[] args)
  {
    ActorSystem system = ActorSystem.create("system");

    ActorRef sessionManager = system.actorOf(SessionManager.createActor(), "sessionManager");

    ActorRef a = system.actorOf(Client.createActor(sessionManager), "client1");

    a.tell(new CreateRequest("session1"), ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    a.tell(new Task("m1"), ActorRef.noSender());
    a.tell(new Task("m2"), ActorRef.noSender());
    a.tell(new Task("m3"), ActorRef.noSender());

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    a.tell(new DeleteRequest("session1"), ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
