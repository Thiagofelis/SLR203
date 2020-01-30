package ex6;

import akka.event.*;
import akka.actor.*;

public class B extends UntypedAbstractActor
{
  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
  B(){}

  public static Props createActor()
  {
    return Props.create(B.class,
    () -> {return new B(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof String)
    {
      System.out.println("B: received [" + msg + "] from " + getSender().path().name());
    }
  }
}
