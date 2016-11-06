package com.imudges.MagicTower.things.map;

import com.imudges.MagicTower.things.ModelRectangle;
/**
 * 单独的地图文件
 * Created by killer on 2016/9/29.
 */
public class MapSingle extends ModelRectangle{
//    private ImageView imageView;
    private int imgWidth;

    public MapSingle(int x, int y, int xLength, int yLength){
        setX(x);
        setY(y);
        setxLength(xLength);
        setyLength(yLength);

        this.imgWidth = 8;
    }

    public MapSingle(int x, int y, int xLength, int yLength, String imgUrl, int imgWidth, int index){
        setX(x);
        setY(y);
        setxLength(xLength);
        setyLength(yLength);
        this.imgWidth = imgWidth;
    }

    public void remove(){

    }

    public int getPngX(int id){
        return (id%this.imgWidth)*32;
    }

    public int getPngY(int id){
        return id/this.imgWidth*32;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(int pngWidth) {
        this.imgWidth = pngWidth;
    }

}
