package ex14;

import akka.actor.*;
import akka.event.*;

public class User extends UntypedAbstractActor
{
  ActorRef ld;

  User(ActorRef l) {ld = l;}

  public static Props createActor(ActorRef l)
  {
    return Props.create(User.class,
    () -> {return new User(l); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof String)
    {
      if( ((String) msg) == "join" || ((String) msg) == "unjoin")
      {
        ld.tell(msg, getSelf());
      }
    } else if (msg instanceof Task)
    {
      if (getSender().path().name() == "ld")
      {
        System.out.println(getSelf().path().name() + ": executing task " + ((Task) msg).getName());
      }
      else
      {
        ld.tell(msg, getSelf());
      }
    }
  }
}
