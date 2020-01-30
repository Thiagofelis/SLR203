package ex12;
import java.util.*;
import akka.actor.*;

public class Group
{
  String name;
  List<ActorRef> list;
  Group(String n, List<ActorRef> l) {name = n; list = new ArrayList<ActorRef>(l);}
  String getName() {return name;}
  List<ActorRef> getList() {return list;}
}
