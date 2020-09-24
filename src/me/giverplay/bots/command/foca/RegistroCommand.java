package me.giverplay.bots.command.foca;

import me.giverplay.bots.foca.FocaBot;
import me.giverplay.bots.command.Command;
import me.giverplay.bots.listener.FocaReactionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;


public class RegistroCommand extends Command
{
  private FocaReactionListener register;
  
  public RegistroCommand(FocaBot bot)
  {
    super("registro", bot);
    
    bot.getJDA().addEventListener(register = new FocaReactionListener(bot));
  }
  
  @Override
  public void execute(JDA bot, TextChannel channel, User sender, String[] args)
  {
    if(register.isRegistering(sender.getId()))
    {
      channel.sendMessage(sender.getAsMention() + " Você já está se registrando.").queue();
      return;
    }
  
    Guild guild = channel.getGuild();
    
    if(guild.getMember(sender).getRoles().contains(guild.getRoleById("758330966338306049")))
    {
      channel.sendMessage(sender.getAsMention() + " Você já se registrou...").queue();
      return;
    }
    
    register.start(channel, sender);
  }
}
