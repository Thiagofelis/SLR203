package ex10;
import java.util.*;

import akka.actor.*;
import akka.event.*;

public class Broadcaster extends UntypedAbstractActor
{
  Broadcaster() {broadcastList = new ArrayList<ActorRef>();}

  public static Props createActor()
  {
    return Props.create(Broadcaster.class,
    () -> {return new Broadcaster(); } );
  }

  List<ActorRef> broadcastList;

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof ActorRef)
    {
      System.out.println("Broadcaster: adding " + ((ActorRef) msg).path().name() + " to list");
      broadcastList.add((ActorRef) msg);
    }
    else if (msg instanceof String)
    {
      System.out.println("Broadcaster: broadcasting message: " + (String) msg);

      ListIterator<ActorRef> it = broadcastList.listIterator();

      while(it.hasNext())
      {
        ActorRef target = it.next();
        System.out.println("Broadcaster: sending message to " + target.path().name());
        target.tell(msg, getSelf());
      }
    }

  }
}
