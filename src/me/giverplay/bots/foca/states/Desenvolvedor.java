package me.giverplay.bots.foca.states;

import me.giverplay.bots.foca.emojis.Emoji;
import me.giverplay.bots.foca.emojis.Emojis;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.ArrayList;
import java.util.List;

public class Desenvolvedor implements State
{
  protected static Desenvolvedor instance;
  
  private static final List<Emoji> reactions;
  
  public Desenvolvedor()
  {
    instance = this;
  }
  
  @Override
  public boolean match(MessageReaction.ReactionEmote emote)
  {
    for(Emoji reaction : reactions)
    {
      if(reaction.match(emote))
      {
        return true;
      }
    }
    
    return false;
  }
  
  @Override
  public List<Emoji> allMatches(List<MessageReaction.ReactionEmote> emotes)
  {
    List<Emoji> list = new ArrayList<>();
    
    for(MessageReaction.ReactionEmote emote : emotes)
    {
      if(match(emote))
      {
        Emoji emoji = getMatch(emote);
        
        if(emoji != null)
        {
          list.add(emoji);
        }
      }
    }
    
    return list;
  }
  
  @Override
  public Emoji getMatch(MessageReaction.ReactionEmote emote)
  {
    for(Emoji reaction : reactions)
    {
      if(reaction.match(emote))
      {
        return reaction;
      }
    }
    
    return null;
  }
  
  static
  {
    reactions = new ArrayList<>();
    
    reactions.add(Emojis.GAMES);
    reactions.add(Emojis.SOFTWARES);
    reactions.add(Emojis.SITES);
    reactions.add(Emojis.APPS);
    reactions.add(Emojis.BOTS);
    reactions.add(Emojis.MODS);
    reactions.add(Emojis.PLUGINS);
  }
}
