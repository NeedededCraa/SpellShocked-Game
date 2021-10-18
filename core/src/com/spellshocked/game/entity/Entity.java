package com.spellshocked.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public abstract class Entity extends Sprite {

    private Camera camera = null;

    public enum Direction {
        LEFT(2, -1, 0), RIGHT(1, 1, 0), UP(3, 0, 1), DOWN(0, 0, -1), NONE(-1, 0, 0);
        public int index, xMod, yMod;

        Direction(int i, int x, int y) {
            index = i;
            xMod = x;
            yMod = y;
        }

    }

    public enum Action {
        MOVING, IDLE
    }

    protected float walkspeed;

    private float xMin = 0;
    private float xMax = 1024;
    private float yMin = 0;
    private float yMax = 768;

    private int terrainHeight;

    private TextureRegion[][] textures;

    private Animation<TextureRegion>[] walkingAnimators;


    private Direction lastDirection;
    private Action lastAction;
    private float stateTime;

    public Entity(TextureRegion[][] t) {
        this(t, 1);
    }

    public Entity(TextureRegion[][] t, float walkspeed) {
        setWalkspeed(walkspeed);
        this.setScale(100);
        textures = t;
        walkingAnimators = new Animation[t.length];
        for (int i = 0; i < t.length; i++) {
            walkingAnimators[i] = new Animation<>(0.15f, parseWalkingSheetRow(t[i]));
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

    protected float moveX = 0, moveY = 0;

    public void move(Direction dir) {
        TextureRegion t;
        if (dir != Direction.NONE) {
            lastDirection = dir;
            lastAction = Action.MOVING;
            t = walkingAnimators[lastDirection.index].getKeyFrame(stateTime, true);
            moveX += walkspeed * dir.xMod;
            moveY += walkspeed * dir.yMod;
        } else {
            lastAction = Action.IDLE;
            t = textures[3][lastDirection.index];
        }
        setRegion(t);
    }


    public void moveLeft() {
        move(Direction.LEFT);
    }

    public void moveRight() {
        move(Direction.RIGHT);
    }

    public void moveUp() {
        move(Direction.UP);
    }

    public void moveDown() {
        move(Direction.DOWN);
    }

    public void stop() {
        move(Direction.NONE);
    }

    public void boundedTranslate(float x, float y) {
        boundedTranslateX(x);
        boundedTranslateY(y);
    }

    public void boundedTranslateX(float x) {
        if (x + getX() > xMax || x + getX() < xMin) return;
        translateX(x);
    }

    public void boundedTranslateY(float y) {
        if (y + getY() > yMax || y + getY() < yMin) return;
        translateY(y);
    }

    private float absY = -1;

    public void periodic() {
        if (absY == -1) absY=getY();
        if (xMax > getX() + moveX && getX() + moveX > xMin) translateX(moveX);
        if (yMax > absY + moveY && absY + moveY > yMin) setY((absY += moveY)+terrainHeight*12);

        stateTime += Gdx.graphics.getDeltaTime();
        if (camera != null) camera.position.set(MathUtils.clamp(getX(), xMin+100, xMax-100), MathUtils.clamp(absY, yMin+100, yMax-100), camera.position.z);
        moveX = 0;
        moveY = 0;
    }

    public void setTerrainHeight(int i) {
        terrainHeight = i;
    }

    public float getTerrainHeight() {
        return terrainHeight;
    }


    public TextureRegion[] parseWalkingSheetRow(TextureRegion[] t) {
        return new TextureRegion[]{t[0], t[1], t[0], t[2]};
    }

    public Entity followWithCamera(Camera c) {
        camera = c;
        return this;
    }

    public Entity stopFollowing() {
        camera = null;
        return this;
    }

}
