package ex12;
import java.util.*;

import akka.actor.*;
import akka.event.*;

public class Receiver extends UntypedAbstractActor
{
  Receiver() {}

  public static Props createActor()
  {
    return Props.create(Receiver.class,
    () -> {return new Receiver(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof String)
    {
      System.out.println(getSelf().path().name() + ": received message: " + msg);
    }
  }
}
