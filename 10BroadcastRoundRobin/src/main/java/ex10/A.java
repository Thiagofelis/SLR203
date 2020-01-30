package ex10;

import akka.actor.*;
import akka.event.*;

public class A extends UntypedAbstractActor
{
  ActorRef broadcaster;
  A(ActorRef b) {broadcaster = b;}

  public static Props createActor(ActorRef _b)
  {
    return Props.create(A.class,
    () -> {return new A(_b); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof String)
    {
      if (getSender() == broadcaster)
      {
        System.out.println(getSelf().path().name() + ": received message " + (String) msg + " from broadcaster");
      }
      else
      {
        System.out.println(getSelf().path().name() + ": sending message " + (String) msg + " to broadcaster");
        broadcaster.tell((String) msg, getSelf());
      }
    }
    else if (msg instanceof ActorRef)
    {
      System.out.println(getSelf().path().name() + ": joining " + ((ActorRef) msg).path().name());
      ((ActorRef) msg).tell(getSelf(), getSelf());
    }
  }
}
