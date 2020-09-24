package me.giverplay.bots.foca;

import me.giverplay.bots.foca.emojis.Emoji;
import me.giverplay.bots.foca.emojis.Emojis;
import me.giverplay.bots.foca.states.Artista;
import me.giverplay.bots.foca.states.Desenvolvedor;
import me.giverplay.bots.foca.states.Inicio;
import me.giverplay.bots.foca.states.Langs;
import me.giverplay.bots.foca.states.State;
import me.giverplay.bots.foca.states.Works;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Registro
{
  private final List<ReactionEmote> currentEmotes = new ArrayList<>();
  private final List<Emoji> finalEmotes = new ArrayList<>();
  
  private static final String embedMessage = "Olá %s, seja bem-vindo à Foca Enterprise! " +
      "Vamos agora criar o seu registro para deixar o seu perfil completo!%n%n%s%n%n%s%n%nFoca Enterprise.";
  
  private static final State artista = new Artista();
  private static final State langs = new Langs();
  private static final State desenvolvedor = new Desenvolvedor();
  private static final State inicio = new Inicio();
  private static final State works = new Works();
  
  private final TextChannel channel;
  private final User user;
  
  private boolean advanceAllowed = false;
  
  private Message message;
  private State state = inicio;
  
  public Registro(TextChannel channel, User user)
  {
    this.channel = channel;
    this.user = user;
    
    createRegister();
  }
  
  private void createRegister()
  {
    EmbedBuilder embed = createCommons()
        .setDescription(String.format(embedMessage, user.getName(), "O que você é? (Pode escolher mais de um).", getJob()));
    
    channel.sendMessage(embed.build()).queue(e -> {
      
      message = e;
      message.addReaction(Emojis.ARTISTA.getName()).queue();
      message.addReaction(Emojis.DESENVOLVEDOR.getName()).queue();
      
      advanceAllowed = true;
    });
  }
  
  private void createWorks()
  {
    EmbedBuilder embed = createCommons()
        .setDescription(String.format(embedMessage, user.getName(), "Que tipo de coisa você produz? (Pode escolher mais de um).", getWorks()));
    
    message.clearReactions().queue();
    message.editMessage(embed.build()).queue(e -> {
      
      if(finalEmotes.contains(Emojis.DESENVOLVEDOR))
      {
        message.addReaction("\uD83C\uDFAE").queue();
        message.addReaction("\uD83C\uDF10").queue();
        message.addReaction("\uD83D\uDCF1").queue();
        message.addReaction("\uD83D\uDDA5").queue();
        message.addReaction(Emojis.PLUGINS.getName()).queue();
        message.addReaction(Emojis.MODS.getName()).queue();
        message.addReaction("\uD83D\uDC7E").queue();
      }
      
      if(finalEmotes.contains(Emojis.ARTISTA))
      {
        message.addReaction(Emojis.SPRITES.getName()).queue();
        message.addReaction(Emojis.SONOPLASTIA.getName()).queue();
        message.addReaction(Emojis.MODELAGEM.getName()).queue();
      }
      
      advanceAllowed = true;
    });
  }
  
  private void createLangs()
  {
    EmbedBuilder embed = createCommons()
       .setDescription(String.format(embedMessage, user.getName(), "Quais linguagens você utiliza?", getLangs()));
   
    message.clearReactions().queue();
    message.editMessage(embed.build()).queue(e -> {
      
      message.addReaction(Emojis.JAVA.getName()).queue();
      message.addReaction(Emojis.C_CPP.getName()).queue();
      message.addReaction(Emojis.C_SHARP.getName()).queue();
      message.addReaction(Emojis.JAVASCRIPT.getName()).queue();
      message.addReaction(Emojis.KOTLIN.getName()).queue();
      message.addReaction(Emojis.SWIFT.getName()).queue();
      message.addReaction("\uD83C\uDFAF").queue();
      message.addReaction(Emojis.LUA.getName()).queue();
      message.addReaction(Emojis.GML.getName()).queue();
      
      advanceAllowed = true;
    });
  }
  
  private EmbedBuilder createCommons()
  {
    return new EmbedBuilder()
        .setTitle("Registro \uD83D\uDCCB")
        .setColor(new Color(0xFF00FFAB))
        .setThumbnail(user.getAvatarUrl());
  }
  
  private String getJob()
  {
    return "\uD83D\uDD8C Artista\n\uD83D\uDCBB Desenvolvedor\n\n⏭️ Próxima etapa";
  }
  
  private String getWorks()
  {
    StringBuilder sb = new StringBuilder();
    
    if(finalEmotes.contains(Emojis.DESENVOLVEDOR))
    {
      sb.append("\uD83C\uDFAE Games\n");
      sb.append("\uD83C\uDF10 Sites\n");
      sb.append("\uD83D\uDCF1 Apps\n");
      sb.append("\uD83D\uDDA5 Softwares\n");
      sb.append("<:spigot:758401199278915644> Spigot Plugins\n");
      sb.append("<:forge:758401198234533899> Forge Mods\n");
      sb.append("\uD83D\uDC7E Bots\n");
    }
    
    if(finalEmotes.contains(Emojis.ARTISTA))
    {
      sb.append("\uD83C\uDFDE Sprites\n");
      sb.append("\uD83C\uDFB6 Sonoplastia\n");
      sb.append("<:modelagem:758401202953257011> Modelagem 3D\n");
    }
    
    sb.append("\n⏭️ Próxima etapa");
    
    return sb.toString();
  }
  
  private String getLangs()
  {
    StringBuilder sb = new StringBuilder();
    
    sb.append("<:java:758407067898740747> Java\n");
    sb.append("<:cpp:758408304014917642> C ou C++\n");
    sb.append("<:csharp:758408892987736116> C#\n");
    sb.append("<:javascript:758402713301418035> JavaScript ou NodeJS\n");
    sb.append("<:kotlin:758404400741482516> Kotlin\n");
    sb.append("<:swift:758401203851231242> Swift\n");
    sb.append("\uD83C\uDFAF Dart\n");
    sb.append("<:lua:758401204379189258> Lua\n");
    sb.append("<:gms:758405113298681867> GML\n\n");
    sb.append("⏭️ Próxima etapa");
    
    return sb.toString();
  }
  
  private void done()
  {
    EmbedBuilder embedBuilder = createCommons()
        .setDescription(String.format(embedMessage, user.getName(), "Seu registro foi finalizado! Agora você é um profissional.", "Seus cargos estão sendo adicionados."));
   
    message.clearReactions().queue();
    message.editMessage(embedBuilder.build()).queue();
  
    Guild guild = channel.getGuild();
    
    guild.addRoleToMember(guild.getMember(user), guild.getRoleById("758330966338306049")).queue();
    
    for(Emoji emoji : finalEmotes)
    {
      guild.addRoleToMember(guild.getMember(user), emoji.getRole(guild)).queue();
    }
  }
  
  public void advance()
  {
    advanceAllowed = false;
    finalEmotes.addAll(state.allMatches(currentEmotes));
    currentEmotes.clear();
    
    if(state == inicio)
    {
      state = works;
      createWorks();
      return;
    }
    
    if(state == works)
    {
      if(finalEmotes.contains(Emojis.DESENVOLVEDOR))
      {
        state = langs;
        createLangs();
        return;
      }
      
      done();
    }
    
    if(state == langs)
    {
      done();
    }
  }
  
  public void addEmote(ReactionEmote emote)
  {
    currentEmotes.add(emote);
    
    if(advanceAllowed())
    {
      message.addReaction(Emojis.NEXT.getName()).queue();
    }
  }
  
  public void removeEmote(ReactionEmote emote)
  {
    currentEmotes.remove(emote);
    
    if(!advanceAllowed())
    {
      message.removeReaction(Emojis.NEXT.getName()).queue();
    }
  }
  
  public Message getMessage()
  {
    return message;
  }
  
  public boolean advanceAllowed()
  {
    if(!advanceAllowed)
    {
      return false;
    }
  
    for(ReactionEmote emote : currentEmotes)
    {
      if(state.match(emote))
      {
        return true;
      }
    }
    
    return false;
  }
}
