package ex6;

import akka.event.*;
import akka.actor.*;

public class Transmitter extends UntypedAbstractActor
{
  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


  Transmitter ()  {  }

  public static Props createActor ()
  {
    return Props.create(Transmitter.class,
    () -> {return new Transmitter(); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof Message)
    {
      System.out.println("Transmitter: redirecting the message [" + ((Message)msg).getMessage() +
       "] from " + getSender().path().name() + " to " + ((Message)msg).getTarget().path().name());
       
      ((Message)msg).getTarget().tell(((Message)msg).getMessage(), getSender());
    }
  }
}
