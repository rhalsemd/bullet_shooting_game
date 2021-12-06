package Main;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Game game;
    private GameDifficult Stage;

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        if(game.diff == 0){
            Stage = new Normal(hud, handler,game);
        }
        else if(game.diff == 1){
            Stage = new Hard(hud, handler,game);
        }
    }
    public void tick(){
        ++scoreKeep; // 적 등장의 기점으로 삼으면 됩니다. 숫자가 얼마가 되었을 때 나타나도록 하면 될 것 같습니다.
        if(scoreKeep >= 100) {
            scoreKeep = 0;
            hud.level(hud.getlevel() + 1);
            Stage.setEnenmy();
        }
    }
}
