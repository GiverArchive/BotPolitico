package me.giverplay.bots.foca.states;

import me.giverplay.bots.foca.emojis.Emoji;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.List;

public class Works implements State
{
  @Override
  public boolean match(MessageReaction.ReactionEmote emote)
  {
    return Artista.instance.match(emote) || Desenvolvedor.instance.match(emote);
  }
  
  @Override
  public List<Emoji> allMatches(List<MessageReaction.ReactionEmote> emotes)
  {
    List<Emoji> list = Artista.instance.allMatches(emotes);
    list.addAll(Desenvolvedor.instance.allMatches(emotes));
    
    return list;
  }
  
  @Override
  public Emoji getMatch(MessageReaction.ReactionEmote emote)
  {
    Emoji emoji = Artista.instance.getMatch(emote);
    
    if(emoji == null)
    {
      emoji = Desenvolvedor.instance.getMatch(emote);
    }
    
    return emoji;
  }
}
