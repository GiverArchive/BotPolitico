package me.giverplay.bots.command.foca;

import me.giverplay.bots.foca.FocaBot;
import me.giverplay.bots.command.Command;
import me.giverplay.bots.listener.FocaReactionListener;
import me.giverplay.bots.foca.Registro;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;

public class RegistroCommand extends Command
{
  private HashMap<String, Registro> registros = new HashMap<>();
  
  public RegistroCommand(FocaBot bot)
  {
    super("registro", bot);
    bot.getJDA().addEventListener(new FocaReactionListener());
  }
  
  @Override
  public void execute(JDA bot, TextChannel channel, User sender, String[] args)
  {
    String id = sender.getId();
    
    if(!id.equals("387674761746186260") && !id.equals("645458333972561931"))
      return;
    
    if(registros.containsKey(id))
    {
      //channel.sendMessage(sender.getAsMention() + " Seu registro já está em andamento.").queue();
      //return;
    }
    
    registros.put(id, new Registro(channel, sender));
  }
}
