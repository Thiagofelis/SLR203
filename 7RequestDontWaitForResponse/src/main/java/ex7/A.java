package ex7;

import akka.actor.*;

public class A extends UntypedAbstractActor
{
  ActorRef b;
  A() {}
  public static Props createActor()
  {
    return Props.create(A.class,
    () -> {return new A(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof String)
    {
      if (getSender() == b)
      {
        System.out.println(msg);
      }
      else
      {
        b.tell(msg, getSelf());
      }
    }
    if (msg instanceof ActorRef)
    {
      b = (ActorRef)msg;
    }
  }
}
