package Main;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;
public class Music extends Thread {
    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;
    public Music(String name, boolean isLoop) { //곡의 제목과 곡의 무한반복 여부 인자 받음
        try {
            this.isLoop = isLoop;
            file = new File(Game.class.getResource("../Main/music/"+name).toURI());//메인클래스를 중심으로 파일을 읽어옴
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis); //곡 파일을 버퍼에 담아 읽어올수있게함
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int getTime() {
        if (player == null)
            return 0;
        return player.getPosition();
    }
    public void close() { //음악을 언제든지 종료할 수 있는 함수
        isLoop = false;
        player.close();
        this.interrupt();
    }
    public void run() {
        try {
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
