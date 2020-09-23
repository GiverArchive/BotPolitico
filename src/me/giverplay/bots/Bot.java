package me.giverplay.bots;

import me.giverplay.bots.command.CommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public abstract class Bot
{
  private final CommandHandler handler;
  
  protected String prefix;
  protected JDA bot;
  
  public Bot(String token, CommandHandler handler, String prefix)
  {
    this.handler = handler;
    this.prefix = prefix;
    
    try
    {
      build(token);
    }
    catch(Throwable t)
    {
      System.out.println("Falha ao iniciar");
      t.printStackTrace();
      System.exit(1);
    }
  }
  
  public String getPrefix()
  {
    return prefix;
  }
  
  private void build(String token) throws LoginException, InterruptedException
  {
    bot = new JDABuilder()
        .setToken(token)
        .build()
        .awaitReady();
  }
  
  public CommandHandler getCommandHandler()
  {
    return handler;
  }
  
  public JDA getJDA()
  {
    return bot;
  }
}
