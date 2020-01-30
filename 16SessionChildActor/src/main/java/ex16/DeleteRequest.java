package ex16;
import akka.actor.*;

class DeleteRequest
{
  String actorName;
  String getActorName() {return actorName;}
  DeleteRequest(String n) {actorName = n;}
}
