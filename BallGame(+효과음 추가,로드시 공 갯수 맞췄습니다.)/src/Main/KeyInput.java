package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    Game game;
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game=game;
        //부드러움 움직임을 위한 boolean
        keyDown[0]=false;
        keyDown[1]=false;
        keyDown[2]=false;
        keyDown[3]=false;
    }

    public void keyPressed(KeyEvent e){ // 키를 누르고 있을 때 속도를 5로 한다.
        int key = e.getKeyCode();
        for(int i = 0 ; i < handler.object.size() ; i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player){
                if(key == KeyEvent.VK_UP) {tempObject.setVelY(-5); keyDown[0] = true;}
                if(key == KeyEvent.VK_LEFT) {tempObject.setVelX(-5); keyDown[1] = true;}
                if(key == KeyEvent.VK_RIGHT) {tempObject.setVelX(5); keyDown[2] = true;}
                if(key == KeyEvent.VK_DOWN) {tempObject.setVelY(5); keyDown[3] = true;}
            }
        }
        if(key == KeyEvent.VK_P){
            if(game.gameState== Game.STATE.Game){
                if(Game.paused){
                    Game.paused = false;
                }
                else{
                    Game.paused=true;
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e){ // 키에서 손을 떼면 그 즉시 속도를 0으로 만든다.
        int key = e.getKeyCode();

        for(int i = 0 ; i < handler.object.size() ; i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player){
                if(key == KeyEvent.VK_UP) keyDown[0] = false;
                if(key == KeyEvent.VK_LEFT) keyDown[1] = false;
                if(key == KeyEvent.VK_RIGHT) keyDown[2] = false;
                if(key == KeyEvent.VK_DOWN) keyDown[3] = false;

                if(!keyDown[0] && !keyDown[3]) tempObject.setVelY(0);
                if(!keyDown[1] && !keyDown[2]) tempObject.setVelX(0);
            }
        }
    }

}
