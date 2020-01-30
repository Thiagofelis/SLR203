package ex11;

import akka.actor.*;
import akka.event.*;

public class Sender extends UntypedAbstractActor
{
  ActorRef merger;

  Sender(ActorRef m) {merger = m;}

  public static Props createActor(ActorRef m)
  {
    return Props.create(Sender.class,
    () -> {return new Sender(m); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof String)
    {
      String message = (String) msg;

      if(message == "join")
      {
        System.out.println(getSelf().path().name() + ": joining");
        merger.tell("join", getSelf());
      }
      else if (message == "unjoin")
      {
        System.out.println(getSelf().path().name() + ": unjoining");
        merger.tell("unjoin", getSelf());
      }
      else if (message == "hi")
      {
        System.out.println(getSelf().path().name() + ": sending hi");
        merger.tell("hi", getSelf());
      }
    }
  }
}
