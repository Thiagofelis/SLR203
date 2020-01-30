package ex6;

import akka.actor.*;
import akka.event.*;

public class A extends UntypedAbstractActor
{
	private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
  private ActorRef transmitter;

  A(ActorRef _transmitter)
  {
    transmitter = _transmitter;
  }

  public static Props createActor(ActorRef _transmitter)
  {
    return Props.create(A.class,
    () -> {return new A(_transmitter); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof Message)
    {
      System.out.println("A: sending [" + ((Message)msg).getMessage() + "] to "
			+ ((Message)msg).getTarget().path().name());

      transmitter.tell(msg, getSelf());
    }
  }

}
