package ex1;

import akka.actor.*;
import akka.event.*;

public class A1 extends UntypedAbstractActor
{
  A1()
  {
    System.out.println("A1 is alive");
  }

//  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

  public static Props createActor()
  {
    return Props.create(A1.class, () -> {return new A1();});
  }

  @Override
  public void onReceive(Object msg) throws Exception
  {
    if (msg instanceof String)
    {
      System.out.println(msg + " world");
    }
    else
    {
      unhandled(msg);
    }
  }
}
