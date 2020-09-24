package me.giverplay.bots.foca.states;

import me.giverplay.bots.foca.emojis.Emoji;
import me.giverplay.bots.foca.emojis.Emojis;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.ArrayList;
import java.util.List;

public class Inicio implements State
{
  private static final List<Emoji> reactions;
  
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
    
    reactions.add(Emojis.ARTISTA);
    reactions.add(Emojis.DESENVOLVEDOR);
  }
}
