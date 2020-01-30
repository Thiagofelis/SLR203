package ex14;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class LoadBalancer extends UntypedAbstractActor
{
  LoadBalancer() {resourcesList = new ArrayList<ActorRef>(); next = 0;}

  public static Props createActor()
  {
    return Props.create(LoadBalancer.class,
    () -> {return new LoadBalancer(); } );
  }
  int next;
  List<ActorRef> resourcesList;

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof Task)
    {
      System.out.println(getSelf().path().name() + ": sending task " + ((Task) msg).getName());
      resourcesList.get(next).tell(msg, getSelf());
      next = (next + 1) % resourcesList.size();
    }
    else if (msg instanceof String)
    {
      if( ((String) msg) == "join")
      {
        System.out.println(getSelf().path().name() + ": " + getSender().path().name() + " has joined");
        resourcesList.add(getSender());
      } else if( ((String) msg) == "unjoin")
      {
        if(resourcesList.remove(getSender()))
        {
          System.out.println(getSelf().path().name() + ": " + getSender().path().name() + " has unjoined");
          if(next >= resourcesList.size())
          {
            next = 0;
          }
        }
        else
        {
          System.out.println(getSelf().path().name() + ": " + getSender()
          + " tried to unjoin but is not on list");
        }
      }
    }
  }

}
