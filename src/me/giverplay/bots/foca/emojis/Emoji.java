package me.giverplay.bots.foca.emojis;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.Role;

public class Emoji
{
  private static JDA jda;
  
  private String name;
  private String role;
  
  public Emoji(String discordName, String role)
  {
    this.name = discordName;
    this.role = role;
  }
  
  public String getName()
  {
    return name;
  }
  
  public boolean match(MessageReaction.ReactionEmote emote)
  {
    return emote.equals(getAsReaction()) || matchID(emote);
  }
  
  // Gambiarra nível g0d
  private boolean matchID(MessageReaction.ReactionEmote emote)
  {
    String id;
    
    try
    {
      id = emote.getName() + ":" + emote.getId();
    }
    catch(Exception e)
    {
      return false;
    }
    
    return id.equals(getName());
  }
  
  public static void setJDA(JDA jda)
  {
    Emoji.jda = jda;
  }
  
  public Role getRole(Guild guild)
  {
    return guild.getRoleById(role);
  }
  
  public MessageReaction.ReactionEmote getAsReaction()
  {
    return MessageReaction.ReactionEmote.fromUnicode(getName(), jda);
  }
}
