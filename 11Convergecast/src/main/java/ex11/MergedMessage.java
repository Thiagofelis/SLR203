package ex11;
import java.util.*;
import akka.actor.*;
import akka.event.*;
public class MergedMessage
{
  List<ActorRef> senders;
  MergedMessage(List<ActorRef> s) {senders = new ArrayList<ActorRef>(s);}
  List<ActorRef> getList() {return senders;}
}
