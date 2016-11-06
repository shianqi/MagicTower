package com.imudges.MagicTower.things.skill;

import com.imudges.MagicTower.things.ModelRectangle;
import com.imudges.MagicTower.things.monster.Hero;
import com.imudges.MagicTower.things.monster.Monster;
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
    Image MonsterImage = new Image(getClass().getResourceAsStream("hero_0.png"));
    ArrayList<ModelRectangle> monsterList;
    Timer timer;

    Hero hero;

    public SkillList(Hero hero){
        skillList = new ArrayList<>();
        monsterList = new ArrayList<>();
        this.hero = hero;
        addTimer();
        addMonster();
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
                Iterator monsterIterator = monsterList.iterator();
                while (monsterIterator.hasNext()){
                    ModelRectangle m = (ModelRectangle) monsterIterator.next();
                    for(int i=0;i<skillList.size();i++){
                        if(skillList.get(i).judgeCollide(m)){
                            listTemp.add(skillList.get(i));
                            skillList.get(i).remove();
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
        long intevalPeriod = 40;
        timer.scheduleAtFixedRate(timerTask, delay, intevalPeriod);
    }

    public void addMonster(){
        while(monsterList.size()<10){
            int randomX = ((int)(Math.random()*20)*32);
            int randomY = ((int)(Math.random()*15)*32);
            Monster monster = new Monster(randomX,randomY,32,32,MonsterImage);
            for(int i=0;i<hero.thingsList.size();i++){
                if(hero.thingsList.get(i).judgeCollide(monster)){
                    break;
                }
                if(hero.thingsList.get(i).judgeCollide(hero.model)){
                    break;
                }
                if(i==hero.thingsList.size()-1){
                    getChildren().add(monster);
                    monsterList.add(monster);
                }
            }
        }
    }

    public void addSkill(int x,int y,String direction){
        DevilCut devilCut2 = new DevilCut(x,y,image,direction);
        skillList.add(devilCut2);
        getChildren().add(devilCut2);
    }

    public void stop(){
        timer.cancel();
    }
}
