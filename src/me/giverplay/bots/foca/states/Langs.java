package me.giverplay.bots.foca.states;

import me.giverplay.bots.foca.emojis.Emoji;
import me.giverplay.bots.foca.emojis.Emojis;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;

import java.util.ArrayList;
import java.util.List;

public class Langs implements State
{
  private static final List<Emoji> reactions;
  
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
  
  static
  {
    reactions = new ArrayList<>();
    
    reactions.add(Emojis.JAVASCRIPT);
    reactions.add(Emojis.JAVA);
    reactions.add(Emojis.C_CPP);
    reactions.add(Emojis.LUA);
    reactions.add(Emojis.C_SHARP);
    reactions.add(Emojis.GML);
    reactions.add(Emojis.SWIFT);
    reactions.add(Emojis.DART);
    reactions.add(Emojis.KOTLIN);
  }
}
