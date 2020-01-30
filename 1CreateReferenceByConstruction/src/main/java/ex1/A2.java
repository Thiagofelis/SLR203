package ex1;
import akka.actor.*;
import akka.event.*;
//import A1;

public class A2 extends UntypedAbstractActor
{
  ActorRef a1;

  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

  A2 (ActorRef _a1)
  {
    a1 = _a1;
    log.info ("i was linked to " + _a1);
    System.out.println("A2 is alive");
  }

  public static Props createActor(ActorRef _a1)
  {
    return Props.create(A2.class, () -> {return new A2(_a1);});
  }
  @Override
  public void onReceive(Object msg) throws Exception
  {
    if (msg instanceof String)
    {
      System.out.println(msg + "world");
    }
    else
    {
      unhandled(msg);
    }
  }
}
