package ex13;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class Topic extends UntypedAbstractActor
{
  Topic(String pub) {subscriberList = new ArrayList<ActorRef>(); publisher = pub;}

  public static Props createActor(String p)
  {
    return Props.create(Topic.class,
    () -> {return new Topic(p); } );
  }
  String publisher;
  List<ActorRef> subscriberList;

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof SubscriptionRequest)
    {
      subscriberList.add(getSender());
      System.out.println(getSelf().path().name() + ": " + getSender().path().name() + " has subscribed");
    }
    else if (msg instanceof UnsubscriptionRequest)
    {
      if (subscriberList.remove(getSender()))
      {
        System.out.println(getSelf().path().name() + ": " + getSender().path().name() + " has unsubscribed");
      }
      else
      {
        System.out.println(getSelf().path().name() + ": " + getSender().path().name() +
        " tried to unsubscribe but is not subscriber");
      }
    }
    else if (msg instanceof String)
    {
      if(getSender().path().name() == publisher)
      {
        ListIterator<ActorRef> it = subscriberList.listIterator();
        System.out.println(getSelf().path().name() + ": multicasting message " + msg);
        while(it.hasNext())
        {
          it.next().tell(msg, getSelf());
        }
      }
      else
      {
        System.out.println(getSelf().path().name() + ": " + getSender().path().name()
         + " is not the publisher");
      }
    }
  }

}
