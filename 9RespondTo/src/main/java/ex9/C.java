package ex9;

import akka.actor.*;
import akka.event.*;

public class C extends UntypedAbstractActor
{
  C(){}
  public static Props createActor()
  {
    return Props.create(C.class,
    () -> {return new C(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof String)
    {
      System.out.println("C: received from " + getSender().path().name() + ": " + msg);
    }
  }
}
