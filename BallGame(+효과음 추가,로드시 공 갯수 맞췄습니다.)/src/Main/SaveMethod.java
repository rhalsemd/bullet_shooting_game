package Main;


import java.io.*;

public class SaveMethod {

    public void Save(HUD hud,Game game){
        SaveData saveData = new SaveData();

        saveData.setScore(hud.getscore());
        saveData.setLevel(hud.getlevel());
        saveData.setLife(hud.HEALTH);
        saveData.setDiff(game.diff);

        try {
            File f = new File("saveResource.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(saveData);
            oos.flush();
            oos.close();

            fos.close();

            System.out.println("저장되었습니다.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public SaveData load(){
        try{
            File f = new File("saveResource.dat");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

            SaveData saveData = (SaveData) ois.readObject();
            System.out.println("Score : "+saveData.getScore());
            System.out.println("Level : "+saveData.getLevel());
            System.out.println("Life : " +saveData.getLife());
            System.out.println("Diff : "+saveData.getDiff());

            ois.close();

            return saveData;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
