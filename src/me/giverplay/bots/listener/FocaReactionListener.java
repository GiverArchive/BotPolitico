package me.giverplay.bots.listener;

import me.giverplay.bots.foca.FocaBot;
import me.giverplay.bots.foca.Registro;
import me.giverplay.bots.foca.emojis.Emojis;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class FocaReactionListener extends ListenerAdapter
{
  private final HashMap<String, Registro> registros = new HashMap<>();
  private final FocaBot bot;
  
  public FocaReactionListener(FocaBot bot)
  {
    this.bot = bot;
  }
  
  public boolean isRegistering(String id)
  {
    return registros.containsKey(id);
  }
  
  public void start(TextChannel channel, User user)
  {
    registros.put(user.getId(), new Registro(channel, user, this));
  }
  
  @Override
  public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event)
  {
    User user = event.getUser();
    
    if(user.isBot() || !isRegistering(user.getId()))
    {
      return;
    }
    
    Registro reg = registros.get(user.getId());
    
    if(!reg.getMessage().getId().equals(event.getMessageId()))
    {
      return;
    }
    
    reg.addEmote(event.getReactionEmote());
  
    if(Emojis.NEXT.match(event.getReactionEmote()))
    {
      reg.advance();
    }
  }
  
  @Override
  public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event)
  {
    User user = event.getUser();
  
    if(user == null || user.isBot() || !isRegistering(user.getId()))
    {
      return;
    }
  
    Registro reg = registros.get(user.getId());
  
    if(!reg.getMessage().getId().equals(event.getMessageId()))
    {
      return;
    }
    
    reg.removeEmote(event.getReactionEmote());
  }
  
  public void remove(String registro)
  {
    registros.remove(registro);
  }
}
