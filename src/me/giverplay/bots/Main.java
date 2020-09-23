package me.giverplay.bots;

import me.giverplay.bots.command.CommandHandler;
import me.giverplay.bots.foca.FocaBot;
import me.giverplay.bots.trump.TrumpBot;

public class Main
{
  public static final String OWNER = "387674761746186260";
  private static String trumpToken;
  private static String focaToken;
  
  private CommandHandler handler = new CommandHandler();
  private TrumpBot trumpBot;
  private FocaBot focaBot;
  
  public static void main(String[] args)
  {
    if(args.length < 2)
      return;
    
    trumpToken = args[0];
    focaToken = args[1];
    
    new Main();
  }
  
  public Main()
  {
    trumpBot = new TrumpBot(trumpToken, handler);
    focaBot = new FocaBot(focaToken, handler);
  }
}
