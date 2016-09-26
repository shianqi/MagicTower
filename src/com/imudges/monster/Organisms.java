package com.imudges.monster;

/**
 * 基本生物类 接口
 * Created by killer on 2016/9/23.
 */
public abstract class Organisms {
    String name;    //名字
    int hp;         //生命值
    int atk;        //攻击力
    int guard;      //防御力
    String png;     //位图

    public abstract void die();

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

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }
}
