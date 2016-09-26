package com.imudges.sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.imudges.map.Map;
import com.imudges.map.MapTop;
import com.imudges.synchron.Synchron;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author shianqi
 * 游戏主进程
 */
public class GamePanel extends Parent {
    private Map map;
    private Sprite sprite;
    private MapTop mapTop;
    private ArrayList<String> list;
    private Timer timer;
    private Synchron synchron;
    public GamePanel() {
    }

    public void stop(){
        timer.cancel();
    }

    public void load(){
        list = new ArrayList<>();
        synchron = new Synchron();
        map = synchron.initMap();
        sprite = new Sprite(map.things);
        mapTop = synchron.initMapTop();
        getChildren().add(map);
        getChildren().add(sprite);
        getChildren().add(mapTop);
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

        addTimer();
    }

    private void addTimer(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(list.size()!=0){
                    if(list.get(list.size()-1).equals("LEFT")){
                        sprite.moveLeft();
                    }else if(list.get(list.size()-1).equals("RIGHT")){
                        sprite.moveRight();
                    }else if(list.get(list.size()-1).equals("UP")){
                        sprite.moveUp();
                    }else if(list.get(list.size()-1).equals("DOWN")){
                        sprite.moveDown();
                    }
                }
                //System.out.println(sprite.getX()+"  "+sprite.getY());
            }
        };
        timer = new Timer();
        //首次执行的时候的延时
        long delay = 0;
        //每次执行的时候的时延
        long intevalPeriod = 20;
        timer.scheduleAtFixedRate(timerTask, delay, intevalPeriod);
    }

    public void onKeyReleased(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("LEFT")){
                    list.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.RIGHT){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("RIGHT")){
                    list.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.UP){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("UP")){
                    list.remove(i);
                }
            }
        }else if(event.getCode() == KeyCode.DOWN) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("DOWN")) {
                    list.remove(i);
                }
            }
        }
    }

    public void onKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.LEFT){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("LEFT")){
                    list.remove(i);
                }
            }
            list.add("LEFT");
        }else if(event.getCode() == KeyCode.RIGHT){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("RIGHT")){
                    list.remove(i);
                }
            }
            list.add("RIGHT");
        }else if(event.getCode() == KeyCode.UP){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("UP")){
                    list.remove(i);
                }
            }
            list.add("UP");
        }else if(event.getCode() == KeyCode.DOWN){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals("DOWN")){
                    list.remove(i);
                }
            }
            list.add("DOWN");
        }
    }

    public void update(long now){

    }

}
