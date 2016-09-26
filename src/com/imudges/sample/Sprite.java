package com.imudges.sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.imudges.monster.Organisms;
import com.imudges.synchron.Synchron;

/**
 * 精灵类
 * Created by shianqi on 2016/9/23.
 */
public class Sprite extends Parent {
    private enum Direction {
        Left, Right, Up, Down
    };

    private Organisms organisms;
    private Direction direction = Direction.Left;
    private Direction lastDirection;
    private int x, y, width, height;
    private int index = 0;
    private int indexDiv = 5;
    private ImageView mImageView;
    private int speed = 2;
    private int shifting = 10;
    private int pngNull = 999;

    private Synchron synchron = new Synchron();
    private int[][] things;

    public Sprite(int[][] things) {
        this.x = 32;
        this.y = 32;
        //初始化英雄
        this.organisms = synchron.initHero();

        this.width = 32;
        this.height = 32;
        this.things = things;

        Image actor = new Image(getClass().getResourceAsStream(this.organisms.getPng()));
        mImageView = new ImageView(actor);
        mImageView.setViewport(new Rectangle2D(width, 0, width, height));
        mImageView.setLayoutX(x);
        mImageView.setLayoutY(y);
        getChildren().add(mImageView);
    }

    /**
     * 像下移动
     */
    public void moveDown() {
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
        if(chickDown()){
            mImageView.setLayoutY(mImageView.getLayoutY() + speed);
            this.y = (int)mImageView.getLayoutY();
        }
        lastDirection = direction;
    }

    /**
     * 像左移动
     */
    public void moveLeft() {
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
        if(chickLeft()){
            mImageView.setLayoutX(mImageView.getLayoutX() - speed);
            this.x = (int)mImageView.getLayoutX();
        }
        lastDirection = direction;
    }

    /**
     * 像右移动
     */
    public void moveRight() {
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
        if(chickRight()){
            mImageView.setLayoutX(mImageView.getLayoutX() + speed);
            this.x = (int)mImageView.getLayoutX();
        }
        lastDirection = direction;
    }


    /**
     * 像上移动
     */
    public void moveUp() {
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
        if(chickTop()){
            mImageView.setLayoutY(mImageView.getLayoutY() - speed);
            this.y = (int)mImageView.getLayoutY();
        }
        lastDirection = direction;
    }

    private boolean chickTop(){
        if(y<=0){
            return false;
        }
        //先判断双块还是单块
        int firstBlockX = (x+shifting)/32;
        int firstBlockY = (y-shifting)/32+1;
        if((x+shifting)%32<shifting*2){
            //单块区域
            if(things[firstBlockY][firstBlockX]==pngNull){
                return true;
            }
        }else{
            //双块区域
            if(things[firstBlockY][firstBlockX]==pngNull&&things[firstBlockY][firstBlockX+1]==pngNull){
                return true;
            }
        }
        return false;
    }
    private boolean chickDown(){
        if(y>=(synchron.getMapHeight()-1)*32){
            return false;
        }
        //先判断双块还是单块
        int firstBlockX = (x+shifting)/32;
        int firstBlockY = (y)/32+1;
        if((x+shifting)%32<shifting*2){
            if(things[firstBlockY][firstBlockX]==pngNull){
                return true;
            }
        }else{
            if(things[firstBlockY][firstBlockX]==pngNull&&things[firstBlockY][firstBlockX+1]==pngNull){
                return true;
            }
        }
        return false;
    }
    private boolean chickLeft(){
        if(x<=0){
            return false;
        }
        int firstBlockX = (x)/32;
        int firstBlockY = (y)/32+1;
        if(y%32==0){
            firstBlockY--;
        }
        if(firstBlockX==0){
            if(things[firstBlockY][firstBlockX]!=pngNull){
                return false;
            }
            return true;
        }else if(things[firstBlockY][firstBlockX]==pngNull){
            return true;
        }
        return false;
    }
    private boolean chickRight(){
        if(x>=(synchron.getMapWidth()-1)*32){
            return false;
        }
        int firstBlockX = (x)/32;
        int firstBlockY = (y)/32+1;
        if(y%32==0){
            firstBlockY--;
        }
        if(things[firstBlockY][firstBlockX+1]==pngNull){
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
}
