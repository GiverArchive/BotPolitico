package me.giverplay.bots;

import me.giverplay.bots.command.CommandHandler;
import me.giverplay.bots.foca.FocaBot;
import me.giverplay.bots.trump.TrumpBot;

public class Main
{
  public static final String OWNER = "387674761746186260";
  
  private static final String TRUMP = Token.TRUMP;
  private static final String FOCA = Token.FOCA;
  
  private CommandHandler handler = new CommandHandler();
  private TrumpBot trumpBot;
  private FocaBot focaBot;
  
  public static void main(String[] args)
  {
    new Main();
  }
  
  public Main()
  {
    trumpBot = new TrumpBot(TRUMP, handler);
    focaBot = new FocaBot(FOCA, handler);
  }
}
