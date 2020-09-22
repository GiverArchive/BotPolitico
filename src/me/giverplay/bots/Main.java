package me.giverplay.bots;

public class Main
{
  private static final String trumpToken = "NDkwOTA2NTkzNzE0NjM0NzU4.W552ZA.LK8ucaNCEuBwPFhn9fNN2xMD4Co";
  
  private TrumpBot trumpBot;
  
  public static void main(String[] args)
  {
    new Main();
  }
  
  public Main()
  {
    trumpBot = new TrumpBot(trumpToken);
  }
}
