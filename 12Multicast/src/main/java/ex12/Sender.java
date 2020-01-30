package ex12;

import akka.actor.*;
import akka.event.*;

public class Sender extends UntypedAbstractActor
{
  ActorRef multicaster;

  Sender(ActorRef m) {multicaster = m;}

  public static Props createActor(ActorRef m)
  {
    return Props.create(Sender.class,
    () -> {return new Sender(m); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof Message)
    {
      System.out.println(getSelf().path().name() + ": sending message \"" + ((Message) msg).getMessage() +
      "\" to " + ((Message) msg).getGroup() + " to multicaster");

      multicaster.tell(msg, getSelf());
    }
    else if (msg instanceof Group)
    {
      System.out.println(getSelf().path().name() + ": sending group " + ((Group) msg).getName()
       + " to multicaster");

      multicaster.tell(msg, getSelf());
    }
  }
}
