package ex19;
import akka.actor.*;
import java.util.*;
import akka.event.*;

public class Node extends UntypedAbstractActor
{
  Node() {neighbours = new ArrayList<ActorRef>();}

  List<ActorRef> neighbours;

  public static Props createActor()
  {
    return Props.create(Node.class,
    () -> {return new Node(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof Map.Entry<?,?>)
    {
      if ( ((Map.Entry<?,?>) msg).getKey() instanceof ActorRef[]
        && ((Map.Entry<?,?>) msg).getValue() instanceof boolean[])
      {
          int i;
          Map.Entry<ActorRef[],boolean[]> p = (Map.Entry<ActorRef[],boolean[]>) msg;
          for (i = 0; i < p.getKey().length; i++)
          {
            if (p.getValue()[i])
            {
              neighbours.add(p.getKey()[i]);
              System.out.println(getSelf().path().name() + ": adding " + i);
            }
          }
      }
    }
  }
}
