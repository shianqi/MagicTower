package com.imudges.MagicTower.sample;

import com.imudges.MagicTower.synchron.Synchron;
import com.imudges.MagicTower.things.map.MapAll;
import com.imudges.MagicTower.things.map.MapBackground;
import com.imudges.MagicTower.things.map.MapForeground;
import com.imudges.MagicTower.things.map.MapThings;
import com.imudges.MagicTower.things.monster.Hero;
import com.imudges.MagicTower.things.skill.SkillList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏加载器
 * Created by killer on 2016/9/29.
 */
public class GameLoader extends Parent {
    private enum Direction {
        Left, Right, Up, Down
    };
    private ArrayList<Direction> keyPressList;
    private Hero heroClient;
    private MapAll mapAll;
    private Timer timer;
    private SkillList skillList;

    public void start(){
        this.keyPressList = new ArrayList<>();

        //添加背景地图
        Synchron synchron = new Synchron();
        MapAll mapAll = synchron.initMap();
        MapBackground mapBackground = mapAll.getMapBackground();
        getChildren().add(mapBackground);
        mapBackground.dorw();

        //添加碰撞图
        MapThings mapThings = mapAll.getMapThings();
        getChildren().add(mapThings);
        mapThings.dorw();

        //添加客户端英雄
        heroClient = new Hero(true);
        heroClient.setMapThings(mapThings);
        getChildren().add(heroClient);

        //添加技能图
        skillList = new SkillList(heroClient);
        getChildren().add(skillList);

        //添加前景图
        MapForeground mapForeground = mapAll.getMapForeground();
        getChildren().add(mapForeground);
        mapForeground.dorw();



        timer = new Timer();
        addTimer();
        addKeyController();
    }

    public void stop(){
        timer.cancel();
        heroClient.stop();
        skillList.stop();
    }

    private void addKeyController(){
        getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                onKeyPressed(event);
            }
        });

        getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                onKeyReleased(event);
            }
        });
    }

    private void addTimer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(keyPressList.size()!=0){
                    if(keyPressList.get(keyPressList.size()-1)==Direction.Left){
                        heroClient.moveLeft();
                    }else if(keyPressList.get(keyPressList.size()-1)==Direction.Right){
                        heroClient.moveRight();
                    }else if(keyPressList.get(keyPressList.size()-1)==Direction.Up){
                        heroClient.moveUp();
                    }else if(keyPressList.get(keyPressList.size()-1)==Direction.Down){
                        heroClient.moveDown();
                    }
                }
            }
        };
        //首次执行的时候的延时
        long delay = 0;
        //每次执行的时候的时延
        long intevalPeriod = 30;
        timer.scheduleAtFixedRate(timerTask, delay, intevalPeriod);
    }

    private void onKeyReleased(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Left){
                    keyPressList.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.RIGHT){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Right){
                    keyPressList.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.UP){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Up){
                    keyPressList.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.DOWN) {
            for (int i = 0; i < keyPressList.size(); i++) {
                if (keyPressList.get(i)==Direction.Down) {
                    keyPressList.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.SPACE){

        }
    }

    private void onKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Left){
                    keyPressList.remove(i);
                }
            }
            keyPressList.add(Direction.Left);
        }else if(event.getCode() == KeyCode.RIGHT){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Right){
                    keyPressList.remove(i);
                }
            }
            keyPressList.add(Direction.Right);
        }else if(event.getCode() == KeyCode.UP){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Up){
                    keyPressList.remove(i);
                }
            }
            keyPressList.add(Direction.Up);
        }else if(event.getCode() == KeyCode.DOWN){
            for(int i=0;i<keyPressList.size();i++){
                if(keyPressList.get(i)==Direction.Down){
                    keyPressList.remove(i);
                }
            }
            keyPressList.add(Direction.Down);
        }else if(event.getCode() == KeyCode.SPACE){
            //sprite.attack();
            heroClient.addSkill(skillList);
        }
    }
}
