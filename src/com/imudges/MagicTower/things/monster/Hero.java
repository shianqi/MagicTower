package com.imudges.MagicTower.things.monster;

import com.imudges.MagicTower.things.ModelRectangle;
import com.imudges.MagicTower.things.map.MapThings;
import com.imudges.MagicTower.things.skill.SkillList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 英雄基本类
 * Created by killer on 2016/9/23.
 */
public class Hero extends Parent{
    private String name;
    private int hp;
    private int atk;
    private int guard;
    private String png;
    private int xp;         //当前经验
    private int lv;         //当前等级
    private int xpMax;      //最大经验
    public ModelRectangle model;
    private int modelXShifting = 5;
    private MapThings mapThings;
    public ArrayList<ModelRectangle> thingsList;
    private Timer timer;
    private TimerTask timerTask;
    private boolean canAttack = true;

    private ImageView mImageView;
    private Hero.Direction direction = Direction.Left;
    private Hero.Direction lastDirection = Direction.Down;
    private int width, height;
    private int index = 0;
    private int indexDiv = 5;
    private int speed = 3;


    private enum Direction {
        Left, Right, Up, Down
    };

    public void setMapThings(MapThings mp){
        this.mapThings = mp;
        thingsList = mp.thingsList;
    }

    public Hero(boolean isClient){
        initData();
        if(isClient){

        }else{
            System.out.println("玩家2加入");
        }

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                canAttack = true;
                timer.cancel();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 100, 800);
    }

    public void addSkill(SkillList skillList){
        if(canAttack){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    canAttack = true;
                    timer.cancel();
                }
            };
            timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 100, 800);
            skillList.addSkill(model.getX(),model.getY(),lastDirection.toString());

            canAttack = false;
        }
    }

    private void initData(){
        this.name = "Theobald";
        this.hp=100;
        this.atk=0;
        this.guard=0;
        this.png="hero_1.png";

        this.width = 32;
        this.height = 32;

        model = new ModelRectangle(32,32,32-2*modelXShifting,32);

        Image img = new Image(getClass().getResourceAsStream(png));
        mImageView = new ImageView(img);
        mImageView.setViewport(new Rectangle2D(32, 0, 32, 32));
        mImageView.setLayoutX(32);
        mImageView.setLayoutY(32);
        getChildren().add(mImageView);
    }

    public void moveLeft(){
        direction = Direction.Left;
        if(lastDirection != direction){
            index = 3 * indexDiv;
        }
        index++;
        if (index / indexDiv > 5) {
            index = 3 * indexDiv;
        }
        mImageView.setViewport(new Rectangle2D(((index / indexDiv) % 3) * width, ((index / indexDiv) / 3) * height, width,
                height));

        this.model.setX((int)mImageView.getLayoutX()-speed + modelXShifting);
        if(chickLeft()){
            mImageView.setLayoutX(mImageView.getLayoutX() - speed);
        }else{
            this.model.setX((int)mImageView.getLayoutX()+speed + modelXShifting);
        }
        lastDirection = direction;
    }

    public void moveRight(){
        direction = Direction.Right;
        if(lastDirection != direction){
            index = 6 * indexDiv;
        }
        index++;
        if (index / indexDiv > 8) {
            index = 6 * indexDiv;
        }
        mImageView.setViewport(new Rectangle2D(((index / indexDiv) % 3) * width, ((index / indexDiv) / 3) * height, width,
                height));
        this.model.setX((int)mImageView.getLayoutX() + speed + modelXShifting);
        if(chickRight()){
            mImageView.setLayoutX(mImageView.getLayoutX() + speed);
        }else{
            this.model.setX((int)mImageView.getLayoutX() - speed + modelXShifting);
        }
        lastDirection = direction;
    }

    public void moveUp(){
        direction = Direction.Up;
        if(lastDirection != direction){
            index = 9 * indexDiv;
        }
        index++;
        if (index / indexDiv > 11) {
            index = 9 * indexDiv;
        }
        mImageView.setViewport(new Rectangle2D(((index / indexDiv) % 3) * width, ((index / indexDiv) / 3) * height, width,
                height));
        this.model.setY((int)mImageView.getLayoutY() - speed);
        if(chickTop()){
            mImageView.setLayoutY(mImageView.getLayoutY() - speed);
        }else{
            this.model.setY((int)mImageView.getLayoutY() + speed);
        }
        lastDirection = direction;
    }

    public void moveDown(){
        direction = Direction.Down;
        if(lastDirection != direction){
            index = 0;
        }
        index++;
        if (index / indexDiv > 2) {
            index = 0;
        }
        mImageView.setViewport(new Rectangle2D(((index / indexDiv) % 3) * width, ((index / indexDiv) / 3) * height, width,
                height));

        this.model.setY((int)mImageView.getLayoutY() + speed);
        if(chickDown()){
            mImageView.setLayoutY(mImageView.getLayoutY() + speed);
        }else{
            this.model.setY((int)mImageView.getLayoutY() - speed);
        }
        lastDirection = direction;
    }

    private boolean chickTop(){
        if(model.getY()<=0){
            return false;
        }
        if(chickCollide()){
            return false;
        }
        return true;
    }

    private boolean chickDown(){
        if(model.getY()>=(15-1)*32){
            return false;
        }
        if(chickCollide()){
            return false;
        }
        return true;
    }

    private boolean chickLeft() {
        if (model.getX() <= 0) {
            return false;
        }
        if(chickCollide()){
            return false;
        }
        return true;
    }

    private boolean chickRight(){
        if(model.getX()>=(20-1)*32){
            return false;
        }
        if(chickCollide()){
            return false;
        }
        return true;
    }

    private boolean chickCollide(){
        for(int i=0;i<thingsList.size();i++){
            if(thingsList.get(i).judgeCollide(model)){
                return true;
            }
        }
        return false;
    }

    public void stop(){
        timer.cancel();
    }

    public void die() {
        System.out.println("英雄 "+name+" 被击杀！");
    }

    public String getPng() {
        return this.png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getGuard() {
        return guard;
    }

    public void setGuard(int guard) {
        this.guard = guard;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getXpMax() {
        return xpMax;
    }

    public void setXpMax(int xpMax) {
        this.xpMax = xpMax;
    }
}
