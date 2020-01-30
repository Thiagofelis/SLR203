package ex12;
import java.util.*;
import akka.actor.*;
import akka.event.*;

public class Multicaster extends UntypedAbstractActor
{

  Multicaster() {groupList = new ArrayList<Group>();}

  public static Props createActor()
  {
    return Props.create(Multicaster.class,
    () -> {return new Multicaster(); } );
  }

  List<Group> groupList;

  @Override
  public void onReceive(Object msg)
  {
    if (msg instanceof Message)
    {
      ListIterator<Group> iterator = groupList.listIterator();

      Group target, temp;
      target = null;
      while (iterator.hasNext())
      {
        temp = iterator.next();
        if(temp.getName() == ((Message) msg).getGroup())
        {
          target = temp;
          break;
        }
      }

      if(target == null)
      {
        System.out.println(getSelf().path().name() + ": group does not exist");
        return;
      }

      ListIterator<ActorRef> actorIterator = target.getList().listIterator();

      System.out.println(getSelf().path().name() + ": multicasting message \"" + ((Message) msg).getMessage() +
      "\" to " + ((Message) msg).getGroup() );

      while (actorIterator.hasNext())
      {
        actorIterator.next().tell(((Message) msg).getMessage(), getSelf());
      }
    }
    else if (msg instanceof Group)
    {
      System.out.println(getSelf().path().name() + ": creating group " + ((Group) msg).getName());

      groupList.add((Group) msg);
    }
  }
}
