package me.giverplay.bots.foca.states;

import me.giverplay.bots.foca.emojis.Emoji;
import me.giverplay.bots.foca.emojis.Emojis;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;

import java.util.ArrayList;
import java.util.List;

public class Artista implements State
{
  protected static Artista instance;
  
  private static final List<Emoji> reactions;
  
  public Artista()
  {
    instance = this;
  }
  
  @Override
  public boolean match(ReactionEmote emote)
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
  public Emoji getMatch(ReactionEmote emote)
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
  
  @Override
  public List<Emoji> allMatches(List<ReactionEmote> emotes)
  {
    List<Emoji> list = new ArrayList<>();
    
    for(ReactionEmote emote : emotes)
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
  
  static
  {
    reactions = new ArrayList<>();
    
    reactions.add(Emojis.SONOPLASTIA);
    reactions.add(Emojis.SPRITES);
    reactions.add(Emojis.MODELAGEM);
  }
}
