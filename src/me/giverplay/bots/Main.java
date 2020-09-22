package me.giverplay.bots;

public class Main
{
  private static String trumpToken;
  
  private TrumpBot trumpBot;
  
  public static void main(String[] args)
  {
    if(args.length == 0)
      return;
    
    trumpToken = args[0];
    
    new Main();
  }
  
  public Main()
  {
    trumpBot = new TrumpBot(trumpToken);
  }
}
