package ex16;

import akka.actor.*;
import akka.event.*;

public class Client extends UntypedAbstractActor
{
  ActorRef sessionManager, session;

  Client(ActorRef l) {sessionManager = l; session = null;}

  public static Props createActor(ActorRef l)
  {
    return Props.create(Client.class,
    () -> {return new Client(l); } );
  }

  @Override
  public void onReceive(Object msg)
  {
    if(msg instanceof CreateRequest)
    {
      sessionManager.tell(msg, getSelf());
    }
    else if (msg instanceof DeleteRequest)
    {
      sessionManager.tell(msg, getSelf());
      session = null;
    }
    else if (msg instanceof String)
    {
      if (getSender() == session)
      {
        System.out.println(getSelf().path().name() + ": received from session: " + ((String) msg));
      }
    }
    else if (msg instanceof Task)
    {
      if (session == null)
      {
        System.out.println(getSelf().path().name() + ": error, no open session to execute task");
      }
      else
      {
        System.out.println(getSelf().path().name() + ": sending request: " + ((Task) msg).getName()
          + " to " + session.path().name());
        session.tell(msg, getSelf());
      }
    }
    else if (msg instanceof ActorRef)
    {
      session = (ActorRef) msg;
    }
  }
}
