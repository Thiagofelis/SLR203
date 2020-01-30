import akka.actor.UntypedAbstractActor;

public class a1 extends UntypedAbstractActor
{
  @Override
  public void onReceive(Object msg) throws Exception
  {
    if (msg instanceof String)
    {
      system.out.println(msg + "world");
    }
    else
    {
      unhandled(msg);
    }
  }
}
