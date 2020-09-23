package me.giverplay.bots.listener;

import me.giverplay.bots.TrumpBot;
import me.giverplay.bots.command.CommandHandler;
import me.giverplay.bots.command.trump.AloBolsonaroCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TrumpMessageListener extends ListenerAdapter
{
  private CommandHandler handler;
  private TrumpBot bot;
  
  public TrumpMessageListener(TrumpBot bot)
  {
    this.bot = bot;
    handler = bot.getCommandHandler();
    
    registerCommands();
  }
  
  private void registerCommands()
  {
    handler.registerCommand(new AloBolsonaroCommand(bot));
  }
  
  @Override
  public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event)
  {
    User user = event.getAuthor();
    Message message = event.getMessage();
    
    if(user.isBot() || message.isWebhookMessage())
    {
      return;
    }
    
    handler.dispatchCommand(event, bot);
  }
}
