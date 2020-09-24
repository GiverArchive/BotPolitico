package me.giverplay.bots.command.foca;

import me.giverplay.bots.command.Command;
import me.giverplay.bots.foca.FocaBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class MonitorarCommand extends Command
{
  public MonitorarCommand(FocaBot bot)
  {
    super("monitorar", bot);
    
    setOwnerCommand(true);
  }
  
  boolean b = false;
  
  @Override
  public void execute(JDA bot, TextChannel channel, User sender, String[] args)
  {
    if(b)
      channel.sendMessage(sender.getAsMention() + " As mensagens agora estão sendo monitoradas.").queue();
    else
      channel.sendMessage(sender.getAsMention() + " As mensagens agora não estão mais sendo monitoradas.").queue();
    
    b = !b;
  }
}
