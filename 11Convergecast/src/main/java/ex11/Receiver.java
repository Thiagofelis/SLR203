package ex11;
import java.util.*;

import akka.actor.*;
import akka.event.*;

public class Receiver extends UntypedAbstractActor
{
  Receiver() {}

  public static Props createActor()
  {
    return Props.create(Receiver.class,
    () -> {return new Receiver(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof MergedMessage)
    {
      List<ActorRef> list= ((MergedMessage) msg).getList();
      System.out.print(getSelf().path().name() + ": received signals from ");
      ListIterator<ActorRef> it = list.listIterator();

      while(it.hasNext())
      {
        ActorRef target = it.next();
        System.out.print(target.path().name() + " ");
      }
      System.out.println("");
    }
  }
}
