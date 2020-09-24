package me.giverplay.bots.listener;

import me.giverplay.bots.command.CommandHandler;
import me.giverplay.bots.command.foca.MonitorarCommand;
import me.giverplay.bots.command.foca.RegistroCommand;
import me.giverplay.bots.foca.FocaBot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class FocaMessageListener extends ListenerAdapter
{
  private CommandHandler handler;
  private FocaBot bot;
  
  public FocaMessageListener(FocaBot bot)
  {
    this.bot = bot;
    handler = bot.getCommandHandler();
    
    registerCommands();
  }
  
  private void registerCommands()
  {
    handler.registerCommand(new RegistroCommand(bot));
    handler.registerCommand(new MonitorarCommand(bot));
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
