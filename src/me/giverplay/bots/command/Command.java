package me.giverplay.bots.command;

import me.giverplay.bots.Bot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command
{
  private final String name;
  private final Bot bot;
  
  private List<String> aliases = new ArrayList<>();
  
  private boolean ownerCommand;
  
  public Command(String name, Bot bot)
  {
    this.name = name;
    this.bot = bot;
  }
  
  protected void addAliases(String... aliases)
  {
    this.aliases.addAll(Arrays.asList(aliases));
  }
  
  public Bot getBot()
  {
    return bot;
  }
  
  public List<String> getAliases()
  {
    return aliases;
  }
  
  public String getName()
  {
    return name;
  }
  
  public boolean isOwnerCommand()
  {
    return ownerCommand;
  }
  
  public void setOwnerCommand(boolean ownerCommand)
  {
    this.ownerCommand = ownerCommand;
  }
  
  public boolean match(String entry)
  {
    if(entry.equalsIgnoreCase(bot.getPrefix() + getName())) return true;
    
    for(String s : aliases)
    {
      if(entry.equalsIgnoreCase(bot.getPrefix() + s)) return true;
    }
    
    return false;
  }
  
  public abstract void execute(JDA bot, TextChannel channel, User sender, String[] args);
}
