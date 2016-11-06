package com.imudges.MagicTower.things.skill;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 恶魔斩
 * Created by killer on 2016/10/8.
 */
public class DevilCut extends BaseSkill{
    private ImageView imageView;
    private String direction;

    public DevilCut(int x,int y,Image image,String direction){
        this.direction = direction;
        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(0,0, 70, 32));
        if(direction.equals("Right")){
            setX(x);
            setY(y);
            setxLength(70);
            setyLength(32);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
        }else if(direction.equals("Left")){
            setX(x-38);
            setY(y);
            setxLength(70);
            setyLength(32);
            imageView.setRotate(180);
            imageView.setLayoutX(getX());
            imageView.setLayoutY(getY());
        }else if(direction.equals("Up")){
            setX(x);
            setY(y);
            setxLength(32);
            setyLength(70);
            imageView.setRotate(270);
            imageView.setLayoutX(x-19);
            imageView.setLayoutY(y-19);
        }else{
            setX(x);
            setY(y);
            setxLength(32);
            setyLength(70);
            imageView.setRotate(90);
            imageView.setLayoutX(x-19);
            imageView.setLayoutY(y+19);
        }


        getChildren().add(imageView);
    }

    @Override
    public void run() {
        if(direction.equals("Right")){
            setX(getX()+5);
            imageView.setLayoutX(getX());
            imageView.setViewport(new Rectangle2D(0,32*(getX()%4), 70, 32));
        }else if(direction.equals("Left")){
            setX(getX()-5);
            imageView.setLayoutX(getX());
            imageView.setViewport(new Rectangle2D(0,32*(getX()%4), 70, 32));
        }else if(direction.equals("Up")){
            setY(getY()-5);
            imageView.setLayoutX(getX()-19);
            imageView.setLayoutY(getY()-19);
            imageView.setViewport(new Rectangle2D(0,32*(getY()%4), 70, 32));
        }else{
            setY(getY()+5);
            imageView.setLayoutX(getX()-19);
            imageView.setLayoutY(getY()+19);
            imageView.setViewport(new Rectangle2D(0,32*(getY()%4), 70, 32));
        }
    }

    @Override
    public void remove(){
        super.remove();
        imageView.setImage(null);
    }
}
