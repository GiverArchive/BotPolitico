package me.giverplay.bots.listener;

import me.giverplay.bots.TrumpBot;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TrumpMessageListener extends ListenerAdapter
{
  private TrumpBot bot;
  private String prefix;
  
  public TrumpMessageListener(TrumpBot bot, String prefix)
  {
    this.prefix = prefix;
    this.bot = bot;
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
    
  }
}
