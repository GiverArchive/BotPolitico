package me.giverplay.bots.command.trump;

import me.giverplay.bots.TrumpBot;
import me.giverplay.bots.command.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class AloBolsonaroCommand extends Command
{
  public AloBolsonaroCommand(TrumpBot bot)
  {
    super("AloBolsonaro", bot);
    
    addAliases("ab", "abolsonaro", "hellobolsonaro");
  }
  
  @Override
  public void execute(JDA bot, TextChannel channel, User sender, String[] args)
  {
    channel.sendMessage("Opa, tudo bem?").queue();
  }
}
