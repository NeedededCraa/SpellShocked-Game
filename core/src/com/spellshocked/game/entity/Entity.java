package com.spellshocked.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public abstract class Entity extends Sprite{

    public enum Direction {
        LEFT(2, -1, 0), RIGHT(1, 1, 0), UP(3, 0, 1), DOWN(0, 0, -1), NONE(-1, 0, 0);
        public int index, xMod, yMod;
        Direction(int i, int x, int y) {
            index = i;
            xMod = x;
            yMod = y;
        }

    }
    public enum Action{
        MOVING, IDLE
    }

    protected float walkspeed;

    private float xMin = 0;
    private float xMax = 2048;
    private float yMin = 0;
    private float yMax = 1536;

    private int terrainHeight;

    private TextureRegion[][] textures;

    private Animation<TextureRegion>[] walkingAnimators;


    private Direction lastDirection;
    private Action lastAction;
    private float stateTime;
    public Entity(TextureRegion[][] t){
        this(t, 1);
    }
    public Entity(TextureRegion[][] t, float walkspeed){
        setWalkspeed(walkspeed);
        this.setScale(100);
        textures = t;
        walkingAnimators = new Animation[t.length];
        for(int i = 0; i < t.length; i++){
            walkingAnimators[i] = new Animation<>(0.15f,  parseWalkingSheetRow(t[i]));
        }
        lastAction = Action.IDLE;
        lastDirection = Direction.UP;
        stateTime = 0f;
        setRegion(t[3][lastDirection.index]);
    }

    public float getWalkspeed() {
        return walkspeed;
    }

    public void setWalkspeed(float walkspeed) {
        this.walkspeed = walkspeed;
    }

    private float moveX = 0, moveY = 0;
    public void move(Direction dir){
        TextureRegion t;
        if(dir != Direction.NONE) {
            lastDirection = dir;
            lastAction = Action.MOVING;
            t = walkingAnimators[lastDirection.index].getKeyFrame(stateTime, true);
            moveX +=walkspeed * dir.xMod;
            moveY +=walkspeed * dir.yMod;
        }else{
            lastAction = Action.IDLE;
            t = textures[3][lastDirection.index];
        }
        setRegion(t);
    }


    public void moveLeft(){
        move(Direction.LEFT);
    }
    public void moveRight(){
        move(Direction.RIGHT);
    }
    public void moveUp(){
        move(Direction.UP);
    }
    public void moveDown(){
        move(Direction.DOWN);
    }
    public void stop(){
        move(Direction.NONE);
    }
    public void boundedTranslate(float x, float y){
        boundedTranslateX(x);
        boundedTranslateY(y);
    }
    public void boundedTranslateX(float x){
        if (x+getX() > xMax || x+getX() < xMin) return;
        translateX(x);
    }
    public void boundedTranslateY(float y){
        if (y+getY() > yMax || y+getY() < yMin) return;
        translateY(y);
    }

    public void periodic(){
        setPosition(MathUtils.clamp(200+moveX, xMin, xMax), MathUtils.clamp(0+moveY, yMin, yMax)+terrainHeight*12);
        stateTime += Gdx.graphics.getDeltaTime();

    }

    public void setTerrainHeight(int i){
        terrainHeight = i;
    }
    public float getTerrainHeight(){
        return terrainHeight;
    }


    public TextureRegion[] parseWalkingSheetRow(TextureRegion[] t){
        return new TextureRegion[]{t[0], t[1], t[0], t[2]};
    }
}
