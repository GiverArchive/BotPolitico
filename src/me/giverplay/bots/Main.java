package me.giverplay.bots;

import me.giverplay.bots.command.CommandHandler;

public class Main
{
  public static final String OWNER = "387674761746186260";
  private static String trumpToken;
  
  private CommandHandler handler = new CommandHandler();
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
    trumpBot = new TrumpBot(trumpToken, handler);
  }
}
