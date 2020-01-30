package ex6;
import akka.actor.*;

public class Main
{
  public static void main(String[] args)
  {
    final ActorSystem system = ActorSystem.create("system");
    final ActorRef b = system.actorOf(B.createActor(), "b");
    final ActorRef transmitter = system.actorOf(Transmitter.createActor(), "transmitter");
    final ActorRef a = system.actorOf(A.createActor(transmitter), "a");

    a.tell(new Message (b, "coucou"), ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
