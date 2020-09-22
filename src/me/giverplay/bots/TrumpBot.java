package me.giverplay.bots;

import me.giverplay.bots.listener.TrumpMessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TrumpBot
{
  private List<Activity> activities = new ArrayList<>();
  private Random random = new Random();
  private JDA bot;
  
  public TrumpBot(String token)
  {
    try
    {
      build(token);
    }
    catch(Throwable t)
    {
      System.out.println("Falha ao iniciar");
      System.exit(1);
    }
    
    populatePresence();
    updatePresence();
    
    new Timer().schedule(new TimerTask() {
      @Override
      public void run()
      {
        updatePresence();
      }
    }, 10, 30000);
  }
  
  private void build(String token) throws LoginException, InterruptedException
  {
    bot = new JDABuilder()
        .setToken(token)
        .addEventListeners(new TrumpMessageListener(this, "trump"))
        .build()
        .awaitReady();
  }
  
  public void updatePresence()
  {
    bot.getPresence().setActivity(activities.get(random.nextInt(activities.size())));
  }
  
  private void populatePresence()
  {
    activities.addAll(Arrays.asList(
        Activity.listening("Bolsonaro no meu telefone."),
        Activity.listening("manifestantes gritando."),
        Activity.of(Activity.ActivityType.STREAMING, "Make america great again", "https://twitch.tv/GiverPlay007"),
        Activity.watching("televisão."),
        Activity.playing("dinheiro fora"),
        Activity.playing("Minecraft"),
        Activity.watching("Alô. É o Bolsonaro?")
    ));
  }
}
