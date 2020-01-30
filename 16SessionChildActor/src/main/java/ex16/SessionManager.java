package ex16;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class SessionManager extends UntypedAbstractActor
{
  SessionManager() {sessionList = new ArrayList<ActorRef>();}

  public static Props createActor()
  {
    return Props.create(SessionManager.class,
    () -> {return new SessionManager(); } );
  }
  List<ActorRef> sessionList;

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof CreateRequest)
    {
      System.out.println(getSelf().path().name() + ": creating " + ((CreateRequest) msg).getActorName());

      ActorRef newSession = getContext().actorOf(Session.createActor(), ((CreateRequest) msg).getActorName());
      sessionList.add(newSession);
      getSender().tell(newSession, getSelf());
    }
    else if (msg instanceof DeleteRequest)
    {
      ListIterator<ActorRef> it = sessionList.listIterator();

      ActorRef temp, target;
      target = null;
      while(it.hasNext())
      {
        temp = it.next();
        if (temp.path().name() == ((DeleteRequest) msg).getActorName())
        {
          target = temp;
          break;
        }
      }

      if(target != null)
      {
        sessionList.remove(target);

        System.out.println(getSelf().path().name() + ": deleting "
          + ((DeleteRequest) msg).getActorName());

        getContext().stop(target);
      }
      else
      {
        System.out.println(getSelf().path().name() + ": " + ((DeleteRequest) msg).getActorName()
        + " is not a valid session");
      }

    }
  }

}
