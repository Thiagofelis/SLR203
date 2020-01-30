package ex9;

import akka.actor.*;
import akka.event.*;

public class B extends UntypedAbstractActor
{
  B(){}
  public static Props createActor()
  {
    return Props.create(B.class,
    () -> {return new B(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof Request)
    {
      Request message = (Request) msg;
      System.out.println("B: treating request " + message.getMessage() + " from " + getSender().path().name()
      + ", sending response to " + message.getActorRef().path().name());

      message.getActorRef().tell("request " + message.getMessage() + " was treated", getSelf());
    }
  }
}
