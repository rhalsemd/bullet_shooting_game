package Main;

public abstract class GameDifficult {
    protected Handler handler;
    protected HUD hud;
    protected Game game;

    GameDifficult(HUD hud, Handler handler, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    public abstract void setEnenmy();
}
