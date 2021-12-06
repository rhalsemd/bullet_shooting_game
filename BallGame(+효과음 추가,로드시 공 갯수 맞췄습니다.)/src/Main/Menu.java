package Main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private HighScore highscore;
    private Handler handler;
    private Random r = new Random();
    private HUD hud = new HUD();
    private String score;
    private int oldscore;
    private SaveMethod saveMethod = new SaveMethod();
    private SaveData loadData = new SaveData();

    Music stage1 = new Music("stage1.mp3", true);

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        highscore = new HighScore();
        try {
            score = highscore.loadScore();
            oldscore = Integer.parseInt(score); // 저장된 점수를 정수화
        }
        catch(NumberFormatException e){

        }
    }
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        // play를 선택시의 이벤트
        if (Game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, (Game.WIDTH - 400) / 2, 400, 400, 100)) {
                Music button = new Music("button.mp3", false);
                button.start();
                game.gameState = Game.STATE.Select;
                return;
            }
            //help를 선택시 의 이벤트
            if (mouseOver(mx, my, (Game.WIDTH - 400) / 2, 550, 400, 100)) {
                Music button = new Music("button.mp3", false);
                button.start();
                game.gameState= Game.STATE.Help;
            }
            //quit를 선택시의 이벤트
            if (mouseOver(mx, my, (Game.WIDTH - 400) / 2, 700, 400, 100)) {
                Music button = new Music("button.mp3", false);
                button.start();
                System.exit(1);
            }
        }
        else if (game.gameState== Game.STATE.Help){
            if(mouseOver(mx, my, (Game.WIDTH - 400) / 2, 550, 400, 100)){
                Music button = new Music("button.mp3", false);
                button.start();
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
        else if (Game.gameState == Game.STATE.End) {
            Music button = new Music("button.mp3", false);
            button.start();
            game.gameState = Game.STATE.Game;
            handler.addObject(new Players(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
            handler.clearEnemys();
            hud.score(0);
            hud.level(1);
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 21), r.nextInt(Game.HEIGHT - 46), ID.BasicEnemy, game.diff, handler));
            if (mouseOver(mx, my, (Game.WIDTH - 400) / 2, 550, 400, 100)) {
                Music buttons = new Music("button.mp3", false);
                buttons.start();
                System.exit(1);
            }
        }
        else if(Game.gameState == Game.STATE.Select)
        {
            if (mouseOver(mx, my, (Game.WIDTH - 400) / 2, 400, 400, 100)) {
                Music button = new Music("button.mp3", false);
                button.start();
                game.diff = 0;
                game.gameState = Game.STATE.Game;
                stage1.start();
                handler.addObject(new Players(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                hud.score(0);
                hud.level(1);
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 21), r.nextInt(Game.HEIGHT - 46), ID.BasicEnemy, 0, handler));
            }
            else if (mouseOver(mx, my, (Game.WIDTH - 400) / 2, 550, 400, 100)) {
                Music button = new Music("button.mp3", false);
                button.start();
                game.diff = 1;
                game.gameState = Game.STATE.Game;
                stage1.start();
                handler.addObject(new Players(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                hud.score(0);
                hud.level(1);
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 21), r.nextInt(Game.HEIGHT - 46), ID.BasicEnemy, 1, handler));
            }
        }

        //게임상의 세이브로드
        else if(Game.gameState==Game.STATE.Game && hud.getlevel()<20){   //save load
            if (mouseOver(mx, my, (Game.WIDTH+250) / 2, 10, 180, 50)) {
                Music button = new Music("button.mp3", false);
                button.start();
                saveMethod.Save(this.hud, this.game);
            }
            else if (mouseOver(mx, my, (Game.WIDTH+660) / 2, 10, 180, 50)) {
                Music button = new Music("button.mp3", false);
                button.start();
                loadData = saveMethod.load();
                handler.clearEnemys();

                //불러온 값으로 세팅
                this.hud.score(loadData.getScore());
                this.hud.level(loadData.getLevel());
                this.game.diff = loadData.getDiff();
                this.hud.HEALTH = loadData.getLife();

                //불러온 난이도에 따라 적세팅
                if(game.diff == 0){
                    Normal normal = new Normal(this.hud,this.handler,this.game);
                    int smartcount=hud.getlevel()/7;
                    int specialcount=hud.getlevel()/4;
                    int basiccount=hud.getlevel()/2;
                    if(smartcount>0){
                        for(int i=0; i<smartcount;i++){
                            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.SmartEnemy, game.diff, handler));
                        }
                    }
                    if(specialcount>0){
                        for(int i=0; i<specialcount; i++){
                            handler.addObject(new SpecialEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.SpeicalEnemy, game.diff, handler));
                        }
                    }
                    if(basiccount>0){
                        for(int i=0; i<basiccount; i++){
                            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(Game.HEIGHT - 45), ID.BasicEnemy, game.diff, handler));
                        }
                    }
                    normal.setEnenmy();
                }
                else{
                    Hard hard = new Hard(this.hud,this.handler,this.game);
                    hard.setEnenmy();
                }

            }
        }
    }
    private String resultNum(){
        if(hud.getscore() > oldscore){
            highscore.saveScore(hud.getscore()); // 저장은 quit를 해야지만 저장이 됩니다.
            return "" + hud.getscore();
        }
        else return "" + oldscore;
    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }

    public void tick(){

    }
    public void render(Graphics g){
        if(Game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 150);
            Font fnt2 = new Font("arial", 1, 80);
            Font fnt3 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(new Color(255, 162 ,162));
            g.drawString("B", 300, 245);
            g.setColor(new Color(255, 220 ,126));
            g.drawString("a", 308 + 100 * 1, 255);
            g.setColor(new Color(255, 255 ,54));
            g.drawString("l", 308 + 90 * 2, 245);
            g.setColor(new Color(65, 255 ,58));
            g.drawString("l", 308 + 74 * 3, 255);
            g.setColor(new Color(255, 162 ,162));
            g.drawString("G", 300 + 60 * 5, 245);
            g.setColor(new Color(255, 220 ,126));
            g.drawString("a", 297 + 70 * 6, 255);
            g.setColor(new Color(255, 255 ,54));
            g.drawString("m", 290 + 73 * 7, 245);
            g.setColor(new Color(65, 255 ,58));
            g.drawString("e", 295 + 80 * 8, 250);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Play", 560, 480);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Help", 560, 630);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Quit", 560, 780);

            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 400, 400, 100);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 550, 400, 100);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 700, 400, 100);
        }
        else if(game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", 1, 150);
            Font fnt2 = new Font("arial", 1, 80);
            Font fnt3 = new Font("arial", 1, 30);
            Font fnt4 = new Font("arial", 1, 50);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 210, 250);

            g.setFont(fnt4);
            g.setColor(Color.white);
            g.drawString("Score : " + hud.getscore(), 510, 350);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Try Again", 460, 480);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Quit", 560, 630);

            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("HighScore : "+ resultNum() , Game.WIDTH - 330, 30);

            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 400, 400, 100);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 550, 400, 100);
        }
        else if(Game.gameState == Game.STATE.Select) {
            Font fnt = new Font("arial", 1, 150);
            Font fnt2 = new Font("arial", 1, 80);
            Font fnt3 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT LEVEL", 95, 250);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Normal", 505, 480);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Hard", 555, 630);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("HighScore : "+ resultNum(), Game.WIDTH - 330, 30);
            // HighScore 받을 자리

            g.setColor(Color.white);
            g.drawRect((Game.WIDTH -  400)/ 2, 400, 400, 100);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 550, 400, 100);
        }
        else if(Game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 150);
            Font fnt2 = new Font("arial", 1, 80);
            Font fnt3 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 500, 250);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Back", 555, 630);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Use MoveKey: ←→↑↓", 450, 400);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Pause: p", 450, 450);
            g.setColor(Color.white);
            g.drawRect((Game.WIDTH - 400) / 2, 550, 400, 100);
        }

    }

}
