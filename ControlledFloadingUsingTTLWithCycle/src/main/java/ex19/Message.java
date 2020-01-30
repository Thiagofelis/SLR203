package ex19;

class Message
{
  String message;
  int hopLimit;
  Message(String s, int hl) {hopLimit = hl; message = s;}
  String readMessage() {return message;}
  Message getCopy()
  {
    if (hopLimit == 0)
    {
      return new NullMessage();
    }
    else
    {
      return new Message(message, hopLimit - 1);
    }
  }
}
