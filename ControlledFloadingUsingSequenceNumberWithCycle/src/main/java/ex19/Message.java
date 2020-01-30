package ex19;

class Message
{
  String message;
  int sequenceNumber;
  Message(String s, int sn) {sequenceNumber = sn; message = s;}
  String readMessage() {return message;}
  int getSequenceNumber() {return sequenceNumber;}
}
