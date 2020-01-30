package ex9;

import akka.actor.*;
import akka.event.*;

public class A extends UntypedAbstractActor
{
  ActorRef b;
  A(ActorRef _b) {b = _b;}

  public static Props createActor(ActorRef _b)
  {
    return Props.create(A.class,
    () -> {return new A(_b); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof Request)
    {
      Request message = (Request) msg;
      System.out.println("A: sending request " + message.getMessage() + " to B");
      b.tell(message, getSelf());
    }
  }

}
