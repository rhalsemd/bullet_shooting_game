package Main;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas{

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // 화면 크기 조절이 불가능합니다.
        frame.setLocationRelativeTo(null); // 컴파일을 하면 JFrame이 모니터 중앙에 오게 됩니다.
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
