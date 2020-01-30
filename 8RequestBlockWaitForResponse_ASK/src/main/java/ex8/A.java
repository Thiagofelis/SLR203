package ex8;

import akka.actor.*;

public class A extends UntypedAbstractActor
{
  ActorRef b;
  boolean free;
  A() {free = true;}
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
        free = true;
        System.out.println(msg);
      }
      else
      {
        if (free)
        {
          free = false;
          b.tell(msg, getSelf());
        }
        else
        {
          System.out.println("request " + msg + " could not be treated");
        }
      }
    }
    if (msg instanceof ActorRef)
    {
      b = (ActorRef)msg;
    }
  }
}
