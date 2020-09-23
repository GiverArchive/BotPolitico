package me.giverplay.bots.foca;

import me.giverplay.bots.foca.emojis.Emojis;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Registro
{
  // Talvez isso esteja muito feio
  private static final String embedMessage = "Olá %s, seja bem-vindo à Foca Enterprise! " +
      "Vamos agora criar o seu registro para deixar o seu perfil completo!%n%n%s%n%n%s%n%nFoca Enterprise.";
  
  private Message message;
  private TextChannel channel;
  private User user;
  
  private List<ReactionEmote> currentEmotes = new ArrayList<>();
  
  public Registro(TextChannel channel, User user)
  {
    this.channel = channel;
    this.user = user;
    
    createRegister();
  }
  
  private void createRegister()
  {
    EmbedBuilder embed = createCommons()
        .setDescription(String.format(embedMessage, user.getName(), "O que você é? (Pode escolher mais de um)", "\uD83D\uDD8C Artista\n\uD83D\uDCBB Desenvolvedor"));
    
    channel.sendMessage(embed.build()).queue(e -> {
      message = e;
      message.addReaction(Emojis.ARTISTA.getName()).queue();
      message.addReaction(Emojis.DESENVOLVEDOR.getName()).queue();
    });
  }
  
  private EmbedBuilder createCommons()
  {
    return new EmbedBuilder()
        .setTitle("Registro \uD83D\uDCCB")
        .setColor(new Color(0xFF00FFAB))
        .setThumbnail(user.getAvatarUrl());
  }
  
  public void advance()
  {
  
  }
  
  public void addEmote(ReactionEmote emote)
  {
    currentEmotes.add(emote);
    System.out.println("Size: " + currentEmotes.size());
  }
  
  public void removeEmote(ReactionEmote emote)
  {
    currentEmotes.remove(emote);
    System.out.println("Size: " + currentEmotes.size());
  }
  
  public Message getMessage()
  {
    return message;
  }
}
