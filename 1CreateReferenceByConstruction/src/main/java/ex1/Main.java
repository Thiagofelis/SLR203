package ex1;
import akka.actor.*;

public class Main
{

  public static void main(String[] args)
  {

    final ActorSystem system = ActorSystem.create("System");
    final ActorRef a1 = system.actorOf(A1.createActor(), "a1");
    final ActorRef a2 = system.actorOf(A2.createActor(a1), "a2");
    //a1.tell("ola", ActorRef.noSender());
    try{
      Thread.sleep(5000);
    } catch (Exception e){}
      system.terminate();
  }
}
