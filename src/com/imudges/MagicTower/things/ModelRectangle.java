package com.imudges.MagicTower.things;

import javafx.scene.Parent;

import java.awt.*;

/**
 * 长方形判定
 * Created by killer on 2016/9/29.
 */
public class ModelRectangle extends Parent{
    private int x;
    private int y;
    private int xLength;
    private int yLength;

    public ModelRectangle(){
        this.x = 0;
        this.y = 0;
        this.xLength = 0;
        this.yLength = 0;
    }

    public ModelRectangle(int x, int y, int xLength, int yLength) {
        this.x = x;
        this.y = y;
        this.xLength = xLength;
        this.yLength = yLength;
    }

    public void run(){

    }

    public void remove(){
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

    public int getxLength() {
        return xLength;
    }

    public void setxLength(int xLength) {
        this.xLength = xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public void setyLength(int yLength) {
        this.yLength = yLength;
    }

    public boolean judgeCollide(ModelRectangle modelRectangle){
        Rectangle rectangle1 = new Rectangle(x,y,xLength,yLength);
        return rectangle1.intersects(modelRectangle.getX(),modelRectangle.getY(),modelRectangle.getxLength(),modelRectangle.getyLength());
    }

    public boolean isInScreen(){
        Rectangle rectangle1 = new Rectangle(x,y,xLength,yLength);
        if(rectangle1.intersects(0,0,32*20,32*15)){
            return true;
        }
        return false;
    }
}
