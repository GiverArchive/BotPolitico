package me.giverplay.bots.command.trump.registro;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;

public class Registro
{
  private static final int TIPO = 0, PRODUZ = 1, TECH = 2, FICHA_0 = 3, FICHA_1 = 4, FICHA_2 = 5, FICHA_3 = 6;
  
  // Sim, isso está muito feio
  private static final String embedMessage = "Olá %s, seja bem-vindo à Foca Enterprise! " +
      "Vamos agora criar o seu registro para deixar o seu perfil completo!\n\n$ins\n\n$roles\n\n";
  
  public Message message;
  private TextChannel channel;
  private User user;
  public int state = TIPO;
  
  public Registro(TextChannel channel, User user)
  {
    this.channel = channel;
    this.state = TIPO;
    this.user = user;
    
    createRegister();
  }
  
  private void createRegister()
  {
    String msg = embedMessage
        .replace("$ins", "O que você é? (Pode escolher mais de um)")
        .replace("$roles", "\uD83D\uDD8C Artista\n\uD83D\uDCBB Desenvolvedor");
    
    EmbedBuilder embed = createCommons().setDescription(msg);
    
    channel.sendMessage(embed.build()).queue(e -> {
      message = e;
      message.addReaction("\uD83D\uDD8C").queue();
      message.addReaction("\uD83D\uDCBB").queue();
    });
  }
  
  private EmbedBuilder createCommons()
  {
    return new EmbedBuilder()
        .setTitle("Registro \uD83D\uDCCB")
        .setFooter(user.getName(), user.getAvatarUrl())
        .setColor(new Color(0xFF00FFAB))
        .setThumbnail(user.getAvatarUrl());
  }
}
