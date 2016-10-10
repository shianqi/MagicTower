package com.imudges.MagicTower.things.map;

/**
 * 总地图类
 * Created by killer on 2016/9/29.
 */
public class MapAll{
    private int mapId;
    private int mapWidth;
    private int mapHeight;
    private int pngWidth;
    private String mapName;

    private MapBackground mapBackground;
    private MapForeground mapForeground;
    private MapThings mapThings;

    public MapAll(int id,int width, int height,int pngWidth, String name,String background,String thingsString,String fontgroundString){
        mapId = id;
        mapWidth = width;
        mapHeight = height;
        this.pngWidth = pngWidth;
        mapName = name;
        mapBackground = new MapBackground();
        mapForeground = new MapForeground();
        mapThings = new MapThings();
        initBackground(background);
        initFontground(fontgroundString);
        initMapThings(thingsString);
    }

    private void initBackground(String str){
        mapBackground.background = new int[mapHeight][mapWidth];
        String[] a = str.split("-");
        for(int i=0;i<a.length;i++){
            String[] backgroundStr= a[i].split(",");
            for(int j = 0;j<backgroundStr.length;j++){
                mapBackground.background[i][j] = Integer.parseInt(backgroundStr[j]);
            }
        }
    }

    private void initFontground(String str){
        mapForeground.fontground = new int[mapHeight][mapWidth];
        String[] a = str.split("-");
        for(int i=0;i<a.length;i++){
            String[] fontgroundStr= a[i].split(",");
            for(int j = 0;j<fontgroundStr.length;j++){
                mapForeground.fontground[i][j] = Integer.parseInt(fontgroundStr[j]);
            }
        }
    }

    private void initMapThings(String str){
        mapThings.things = new int[mapHeight][mapWidth];
        String[] a = str.split("-");
        for(int i=0;i<a.length;i++){
            String[] fontgroundStr= a[i].split(",");
            for(int j = 0;j<fontgroundStr.length;j++){
                mapThings.things[i][j] = Integer.parseInt(fontgroundStr[j]);
            }
        }
    }

    public MapBackground getMapBackground(){
        return mapBackground;
    }

    public MapForeground getMapForeground() {
        return mapForeground;
    }

    public MapThings getMapThings(){
        return mapThings;
    }
}
