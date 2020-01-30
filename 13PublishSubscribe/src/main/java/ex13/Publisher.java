package ex13;

import akka.actor.*;
import akka.event.*;

public class Publisher extends UntypedAbstractActor
{
  ActorRef topic;

  Publisher(ActorRef t) {topic = t;}

  public static Props createActor(ActorRef t)
  {
    return Props.create(Publisher.class,
    () -> {return new Publisher(t); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof String)
    {
      topic.tell(msg, getSelf());
    }
  }
}
