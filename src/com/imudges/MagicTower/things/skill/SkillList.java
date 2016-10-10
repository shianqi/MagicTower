package com.imudges.MagicTower.things.skill;

import com.imudges.MagicTower.things.ModelRectangle;
import com.imudges.MagicTower.things.monster.Hero;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 技能列表
 * Created by killer on 2016/10/8.
 */
public class SkillList extends Parent{
    ArrayList<ModelRectangle> skillList;
    ImageView imageView;
    Image image = new Image(getClass().getResourceAsStream("DevilCut.png"));
    Timer timer;
    Hero hero;

    public SkillList(Hero hero){
        skillList = new ArrayList<>();
        this.hero = hero;
        addTimer();
    }

    private void addTimer(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ArrayList<ModelRectangle> listTemp = new ArrayList<>();
                Iterator iter = skillList.iterator();
                while(iter.hasNext()){
                    ModelRectangle m = (ModelRectangle) iter.next();
                    if(!m.isInScreen()){
                        listTemp.add(m);
                        m.remove();
                    }
                    for(int i=0;i<hero.thingsList.size();i++){
                        if(hero.thingsList.get(i).judgeCollide(m)){
                            listTemp.add(m);
                            m.remove();
                        }
                    }
                }
                skillList.removeAll(listTemp);
                for(int i = 0;i<skillList.size();i++){
                    skillList.get(i).run();
                }
            }
        };
        //首次执行的时候的延时
        long delay = 0;
        //每次执行的时候的时延
        long intevalPeriod = 30;
        timer.scheduleAtFixedRate(timerTask, delay, intevalPeriod);
    }

    public void addSkill(int x,int y){
        DevilCut devilCut2 = new DevilCut(x,y,image);
        skillList.add(devilCut2);
        getChildren().add(devilCut2);
    }

    public void stop(){
        timer.cancel();
    }
}
