package ex13;

import akka.actor.*;
import akka.event.*;

public class Subscriber extends UntypedAbstractActor
{
  ActorRef topic1, topic2;

  Subscriber(ActorRef t1, ActorRef t2) {topic1 = t1; topic2 = t2;}

  public static Props createActor(ActorRef t1, ActorRef t2)
  {
    return Props.create(Subscriber.class,
    () -> {return new Subscriber(t1, t2); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof SubscriptionRequest)
    {
      if(((SubscriptionRequest) msg).getTopic() == "topic1")
      {
        topic1.tell(msg, getSelf());
      }
      else if(((SubscriptionRequest) msg).getTopic() == "topic2")
      {
        topic2.tell(msg, getSelf());
      }
    }
    else if(msg instanceof UnsubscriptionRequest)
    {
      if(((UnsubscriptionRequest) msg).getTopic() == "topic1")
      {
        topic1.tell(msg, getSelf());
      }
      else if(((UnsubscriptionRequest) msg).getTopic() == "topic2")
      {
        topic2.tell(msg, getSelf());
      }
    }
    else if(msg instanceof String)
    {
      System.out.println(getSelf().path().name() + ": has received the message \"" +
      msg + " from " + getSender().path().name());
    }
  }
}
