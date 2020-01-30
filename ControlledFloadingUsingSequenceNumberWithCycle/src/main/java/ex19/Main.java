package ex19;
import akka.actor.*;
import akka.event.*;
import java.util.*;


public class Main
{
  public static void main(String[] args)
  {
    boolean[][] topology = {{false,true,true,false},{false,false,false,false},
                            {false,false,false,true},{false,false,true,false}};

    ActorSystem system = ActorSystem.create("system");

    ActorRef[] nodeList = new ActorRef[4];
    nodeList[0] = system.actorOf(Node.createActor(), "node0");
    nodeList[1] = system.actorOf(Node.createActor(), "node1");
    nodeList[2] = system.actorOf(Node.createActor(), "node2");
    nodeList[3] = system.actorOf(Node.createActor(), "node3");

    int i;
    for (i = 0; i < 4; i++)
    {
      //Map.Entry < Month, Boolean > pair =
      nodeList[i].tell(new AbstractMap.SimpleImmutableEntry <>(nodeList, topology[i]), ActorRef.noSender());
    }

    try{
      Thread.sleep(50);
    }catch (Exception e) {}

    nodeList[0].tell(new Message("hello world", 6), ActorRef.noSender());

    try{
      Thread.sleep(5000);
    }catch (Exception e) {}
    system.terminate();
  }
}
