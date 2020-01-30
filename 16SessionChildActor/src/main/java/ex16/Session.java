package ex16;

import akka.actor.*;
import akka.event.*;

public class Session extends UntypedAbstractActor
{
  Session() {System.out.println(getSelf().path().name() + ": i live!");}


  public static Props createActor()
  {
    return Props.create(Session.class,
    () -> {return new Session(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof Task)
    {
      getSender().tell(((Task) msg).getName() + " was treated", getSelf());
    }
  }
}
