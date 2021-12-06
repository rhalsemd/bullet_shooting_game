package Main;

import java.util.Random;

public class Hard  extends GameDifficult{
    private Random r = new Random();
    Hard(HUD hud, Handler handler,Game game){
        super(hud,handler,game);
        game.diff = 1;
    }
    public void setEnenmy(){
        if (hud.getlevel() == 20) {
            handler.clearEnemys();
            handler.addObject(new EnemyBoss(Game.WIDTH / 2 - 48, -120, ID.EnemyBoss, game.diff, handler));
        }
        if (hud.getlevel() % 4 == 0 && hud.getlevel() < 20) {
            handler.addObject(new SpecialEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.SpeicalEnemy, game.diff, handler));
        }
        if (hud.getlevel() % 3 == 0 && hud.getlevel() < 20) {
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.BasicEnemy, game.diff, handler));
        }
        if (hud.getlevel() % 5 == 0 && hud.getlevel() < 20) {
            handler.addObject(new ScoreItem(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.ScoreItem, handler, game.diff, hud));
        }
        if(hud.getlevel() % 2 == 0 && hud.getlevel() > 20){
            handler.addObject(new ScoreItem(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.ScoreItem, handler, game.diff, hud));
        }
        if (hud.getlevel() % 7 == 0 && hud.getlevel() < 20) {
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.SmartEnemy, game.diff, handler));
        }
    }
}
