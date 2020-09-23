package me.giverplay.bots.command;

import me.giverplay.bots.Bot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CommandHandler
{
  private List<Command> commands = new ArrayList<>();
  
  public CommandHandler()
  {
  
  }
  
  public void dispatchCommand(GuildMessageReceivedEvent event, Bot bot)
  {
    Message message = event.getMessage();
    String[] args = message.getContentRaw().split(" ");
    
    if(args.length == 0) return;
    
    String first = args[0].toLowerCase();
    
    if(!first.startsWith(bot.getPrefix())) return;
  
    args = Arrays.copyOfRange(args, 1, args.length);
    Command cmd = null;
    
    for(Command comm : commands)
    {
      if(!comm.getBot().equals(bot) || !comm.match(first)) continue;
      cmd = comm;
    }
    
    if(cmd == null) return;
    
    try
    {
      cmd.execute(bot.getJDA(), message.getTextChannel(), message.getAuthor(), args);
    }
    catch(Throwable e)
    {
      System.out.println("Falha ao executar comando " + cmd.getName() + " para " + bot.getJDA().getSelfUser().getName());
      e.printStackTrace();
    }
  }
  
  public void registerCommand(Command cmd)
  {
    commands.add(cmd);
  }
}
