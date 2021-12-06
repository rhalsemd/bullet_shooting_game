package Main;

import java.io.Serializable;

public class SaveData implements Serializable {
    int saveScore;
    int saveLevel;
    int saveDiff;
    float saveLife;

    public void setScore(int score){saveScore = score;}
    public int getScore(){return saveScore;}

    public void setLevel(int level){ saveLevel = level;}
    public int getLevel(){ return saveLevel;}

    public void setDiff(int diff){saveDiff = diff;}
    public int getDiff(){return saveDiff;}

    public void setLife(float life){saveLife = life;}
    public float getLife(){return saveLife;}

}
