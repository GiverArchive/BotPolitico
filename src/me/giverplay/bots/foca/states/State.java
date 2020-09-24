package me.giverplay.bots.foca.states;

import me.giverplay.bots.foca.emojis.Emoji;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.List;

public interface State
{
  boolean match(MessageReaction.ReactionEmote emote);
  
  List<Emoji> allMatches(List<MessageReaction.ReactionEmote> emotes);
  
  Emoji getMatch(MessageReaction.ReactionEmote reaction);
}
