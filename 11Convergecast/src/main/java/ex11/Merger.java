package ex11;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class Merger extends UntypedAbstractActor
{
  ActorRef receiver;
  Merger(ActorRef r) {senders = new ArrayList<ActorRef>(); joinList = new ArrayList<ActorRef>(); receiver = r;}

  public static Props createActor(ActorRef r)
  {
    return Props.create(Merger.class,
    () -> {return new Merger(r); } );
  }

  List<ActorRef> senders;
  List<ActorRef> joinList;

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof String)
    {
      String message = (String) msg;

      if(message == "join")
      {
        System.out.println("merger: " + getSender().path().name() + " has joined");
        joinList.add(getSender());
      }
      else if (message == "unjoin")
      {
        if(joinList.contains(getSender()))
        {
          System.out.println("merger: " + getSender().path().name() + " has unjoined");
          joinList.remove(getSender());

          if(senders.contains(getSender()))
          {
            senders.remove(getSender());
          }
        }
      }
      else if (message == "hi")
      {
        System.out.println("merger: " + getSender().path().name() + " has sent a signal");
        senders.add(getSender());

        if (senders.size() == joinList.size())
        {
          System.out.println("merger: all the senders have sent their signal, sending it now to the receiver");
          receiver.tell(new MergedMessage(joinList), getSelf());
          senders.clear();
        }
      }
    }
  }
}
