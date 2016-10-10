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

    public DevilCut(int x,int y,Image image){
        setxLength(70);
        setyLength(32);
        setX(32);
        setY(32);
        imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D(0,0, 75, 32));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        getChildren().add(imageView);
    }

    @Override
    public void run() {
        setX((int)imageView.getLayoutX()+1);
        setY((int)imageView.getLayoutY()+1);
        imageView.setLayoutX(imageView.getLayoutX()+5);
        imageView.setViewport(new Rectangle2D(0,32*(imageView.getLayoutX()%4), 75, 32));
    }

    @Override
    public void remove(){
        super.remove();
        imageView.setImage(null);
    }
}
