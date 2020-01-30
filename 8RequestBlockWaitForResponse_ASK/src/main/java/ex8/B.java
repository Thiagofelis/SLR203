package ex8;

import akka.actor.*;

public class B extends UntypedAbstractActor
{
  B() {}

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
      try{
        Thread.sleep(40);
      } catch(Exception e) {}
      getSender().tell(msg + " was treated", getSelf());
    }
  }
}
