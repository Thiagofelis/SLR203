package ex18;

import akka.actor.*;
import akka.event.*;

public class Creator extends UntypedAbstractActor
{
  Creator() {i = 0;}

  int i;

  public static Props createActor()
  {
    return Props.create(Creator.class,
    () -> {return new Creator(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof String)
    {
      getContext().actorOf(EmptyActor.createActor(), "actor" + i++);
    }
  }
}
