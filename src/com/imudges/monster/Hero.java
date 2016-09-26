package com.imudges.monster;

import com.imudges.sample.GamePanel;

/**
 * 英雄基本类
 * Created by killer on 2016/9/23.
 */
public class Hero extends Organisms{
    int xp;         //当前经验
    int lv;         //当前等级
    int xpMax;      //最大经验

    public Hero(){
        this.name = "Theobald";
        this.hp=0;
        this.atk=0;
        this.guard=0;
        this.png="hero_0.png";
    }

    @Override
    public void die() {
        System.out.println("英雄 "+name+" 被击杀！");
    }

    public String getPng() {
        return this.png;
    }

    public void setPng(String png) {
        this.png = png;
    }
}
