package Main;

import java.io.*;

public class HighScore {

    HighScore() {}

    public void saveScore(int score){
        String path = "src/Main/Highscore.txt";
        String write_string = score + "";

        byte[] buffer = null;

        try{
            buffer = write_string.getBytes("utf-8");
        }catch(UnsupportedEncodingException e){
            System.out.println("encoding 지정 에러");
        }

        OutputStream out = null;

        try{
            out = new FileOutputStream(path, false);
            out.write(buffer);
        } catch(FileNotFoundException e){
            System.out.println("[ERROR] 저장 경로를 찾을 수 없습니다.");
        } catch(IOException e){
            System.out.println("[ERROR] 저장에 실패했습니다.");
        } catch(Exception e){
            System.out.println("[EROOR] 알 수 없는 에러가 발생했습니다.");
        } finally{
            if(out != null){
                try{
                    out.close();
                } catch (IOException e){
                    System.out.println("파일 닫기 실패");
                }
            }
        }
    }

    public String loadScore(){
        String path = "src/Main/HighScore.txt";
        byte[] data = null;
        String read_string = null;
        InputStream in = null;

        try{
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            System.out.println("[INFO] 파일 읽기 성공 >> " + path);
        }catch(Exception e){
            System.out.println("읽기 실패");
        } finally{
            if(in != null){
                try{
                    in.close();
                } catch (IOException e){
                    System.out.println("[ERROR] 파일 닫기 실패");
                }
            }
        }
        if(data != null) {
            try{
                read_string = new String(data, "utf-8");
                System.out.println(read_string);
            }catch(Exception e){
                System.out.println("[EROOR] encoding 지정 에러");
            }
        }
        return read_string;
    }
}


