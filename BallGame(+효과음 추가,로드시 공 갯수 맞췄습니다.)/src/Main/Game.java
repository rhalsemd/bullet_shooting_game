package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.Random;
import Main.Menu;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    public  static  boolean paused = false;
    public int diff=0;

   // private WorldSave testObject;




    public static boolean checkFileExists(){
        return new File("filename").isFile();
    }




    //0이면  노말
    //1이면 하드
    Music intromusic = new Music("bgm.mp3", true);
    public enum STATE {
        Menu,
        Select,
        Game,
        Help,
        Save,
        Load,
        End
    };

    public static STATE gameState = STATE.Menu;
    public Game(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Ball Game", this);
        spawner = new Spawn(handler, hud, this);
        r = new Random();
        if(!(gameState == STATE.Game)) {
            for (int i = 0; i < 30; i++) {
                handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 21), r.nextInt(Game.HEIGHT - 46), ID.MenuParticle, handler));
            }
        }
    }
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        intromusic.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){ // 자주 쓰이는 게임 루프
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running) {
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    private void tick(){
        if(gameState == STATE.Game){
            intromusic.close();
            if(!paused){
                hud.tick(); //체력바
                spawner.tick(); // 스코어
                handler.tick(); //적생성
                if(HUD.HEALTH <= 0){
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();
                    for(int i = 0 ; i < 20 ; i++){
                        handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-21), r.nextInt(Game.HEIGHT-46), ID.MenuParticle, handler));
                    }
                }
            }
        }
        else if(gameState == STATE.Menu || gameState == STATE.End || gameState==STATE.Select) {
            menu.tick();
            handler.tick();
        }
}
    private void render(){ // paint를 끊임 없이 한다고 생각하면 될 것 같습니다.
        BufferStrategy bs = this.getBufferStrategy();
        Font fnt = new Font("arial", 1, 150);
        Font fnt2 = new Font("arial", 1, 160);
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        handler.render(g);
        if(paused){
            g.setColor(Color.gray);
            g.setFont(fnt);
            g.drawString("Paused", WIDTH/2 - 250, HEIGHT/2);
        }
        if(gameState == STATE.Game){
            hud.render(g);
        }
        else if(gameState == STATE.Menu || gameState == STATE.End||gameState==STATE.Select||gameState==STATE.Help){
            menu.render(g);
        }
        bs.show();
    }
    public static float clamp(float var, float min, float max){ // 최소 최대값 설정
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }
    public static void main(String[] args){
            new Game();
    }
}
