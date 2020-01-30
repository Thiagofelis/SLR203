package ex18;

import akka.actor.*;
import akka.event.*;

public class EmptyActor extends UntypedAbstractActor
{
  EmptyActor() {}

  public static Props createActor()
  {
    return Props.create(EmptyActor.class,
    () -> {return new EmptyActor(); } );
  }

  @Override
  public void onReceive(Object msg) {}
}
