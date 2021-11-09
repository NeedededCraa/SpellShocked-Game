package com.spellshocked.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.action.Action;
import com.spellshocked.game.world.Tile;
import static com.badlogic.gdx.math.MathUtils.clamp;
import static java.lang.Math.abs;

public abstract class Entity extends Sprite {

    protected Camera camera = null;
    protected OrthographicCamera ortCam = null;


    public enum Direction {
        LEFT(2, -1, 0), RIGHT(1, 1, 0), UP(3, 0, 1), DOWN(0, 0, -1), NONE(-1, 0, 0);
        public int index, xMod, yMod;

        Direction(int i, int x, int y) {
            index = i;
            xMod = x;
            yMod = y;
        }
    }

    public enum State {
        MOVING, IDLE
    }

    protected float walkSpeed;

    private float xMin = 0;
    private float xMax = 1024;
    private float yMin = 0;
    private float yMax = 768;

    private Tile tile;

    private TextureRegion[][] textures;

    private Animation<TextureRegion>[] walkingAnimators;

    private Direction lastDirection;
    private State lastAction;
    private float stateTime;


    float currentTileZ = 0;
    static final float TOLERANCE_ZONE = 0.2f;

    public float VOLUME;
    public int walk_sound_count;
    public int walk_sound_countdown;



    public Entity(TextureRegion[][] t) {
        this(t, 1);
    }

    public Entity(TextureRegion[][] t, float walkSpeed) {
        setWalkSpeed(walkSpeed);
        setScale(100);
        setSize(16, 24);
        textures = t;
        walkingAnimators = new Animation[t.length];
        for (int i = 0; i < t.length; i++) {
            walkingAnimators[i] = new Animation<>(0.15f, parseWalkingSheetRow(t[i]));
        }
        lastAction = State.IDLE;
        lastDirection = Direction.UP;
        stateTime = 0f;
        setRegion(t[3][lastDirection.index]);
    }
 
    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    private float newX = -1, newY = -1;

    public void move(Direction dir) {
        TextureRegion t;
        if (dir != Direction.NONE) {
            lastDirection = dir;
            lastAction = State.MOVING;
            t = walkingAnimators[lastDirection.index].getKeyFrame(stateTime, true);
            float x = newX+ walkSpeed *dir.xMod, y = newY+ walkSpeed *dir.yMod;
            if(xMax > x && x > xMin && (((x+9)%16 > walkSpeed || tile.left.isStandable() || x>newX) && ((x+9)%16 < 16- walkSpeed || tile.right.isStandable() || x<newX))) newX = x;
            if(yMax > y && y > yMin && (((y+3)%12 > walkSpeed || tile.front.isStandable() || y>newY) && ((y+3)%12 < 12- walkSpeed || tile.back.isStandable() || y<newY))) newY = y;
        } else {
            lastAction = State.IDLE;
            t = textures[3][lastDirection.index];
        }
        setRegion(t);
        play_walk_sound();
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

    public boolean isSurroundedByObstacle(){
        if (!tile.left.isStandable() && !tile.right.isStandable() && !tile.back.isStandable() && !tile.front.isStandable()) return true;
        else if (tile.right.back.isStandable() && !tile.right.isStandable() && !tile.back.isStandable()) return true;
        else if (tile.left.back.isStandable() && !tile.left.isStandable() && !tile.back.isStandable()) return true;
        else if (tile.right.front.isStandable() && !tile.right.isStandable() && !tile.front.isStandable()) return true;
        else if (tile.left.front.isStandable() && !tile.left.isStandable() && !tile.front.isStandable()) return true;
        return false;
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

    public void periodic() {
        if(newX != -1 && newY != -1) {
            setX(newX);
            setY(newY + getTerrainHeight() * 12);
        }

        stateTime += Gdx.graphics.getDeltaTime();
        if (camera != null && ortCam != null) {
            /* transition */
            if (Math.abs(currentTileZ - tile.getZ()) <= TOLERANCE_ZONE){}
            else if (currentTileZ <= tile.getZ()) currentTileZ += 0.05; //note that the value might need some tweaks depend on actual frameRate
            else if (currentTileZ >= tile.getZ()) currentTileZ -= 0.05; //note that the value might need some tweaks depend on actual frameRate
            /* actual camera move */
            if (abs(ortCam.zoom - 0.5) <= TOLERANCE_ZONE){
                camera.position.set(clamp(newX, xMin + 100, xMax - 85), clamp(newY, yMin + 30, yMax - 30) + currentTileZ*16, camera.position.z); //zoom == 0.5
            }
            else if (abs(ortCam.zoom - 1) <= TOLERANCE_ZONE){
                camera.position.set(clamp(newX, xMin + 200, xMax - 185), clamp(newY, yMin + 110 - currentTileZ, yMax - 100 - currentTileZ) + currentTileZ*16, camera.position.z); //zoom == 1
            }
            else if (abs(ortCam.zoom - 1.5) <= TOLERANCE_ZONE){
                camera.position.set(clamp(newX, xMin + 300, xMax - 285), clamp(newY, yMin + 150, yMax - 150) + currentTileZ*16, camera.position.z); //zoom == 1.5
            }
            else if (abs(ortCam.zoom - 2) <= TOLERANCE_ZONE){
                camera.position.set(clamp(newX, xMin + 400, xMax - 385), clamp(newY, yMin + 220, yMax - 220) + currentTileZ*16, camera.position.z); //zoom == 2
            }
            else {
                camera.position.set(clamp(newX, xMin + 100, xMax - 100), clamp(newY, yMin + 200, yMax - 200) + currentTileZ*16, camera.position.z);
            }
            /* print debug info */
//            System.out.println("imaginary camera Y: " + currentTileZ + " tile z: " + tile.getZ());
//            System.out.println(clamp(absY, yMin + 110 - currentTileZ, yMax - 110 - currentTileZ) + currentTileZ*16);
        }
        newX = getX();
        newY = getY()-getTerrainHeight()*12;
        walk_sound_countdown--;
    }

    public void setTile(Tile i) {
        tile = i;
    }

    public float getTerrainHeight() {
        return tile!=null ? tile.zValue : 0;
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
    public double health;
    public boolean invincible;


    public void modifyHealth(double damage){
        health+=damage;
        if(health <= 0) die();
    }
    public void die(){

    }
    public void setHealth(double value){
        health = value;
    }

    public Entity setOrthographicCamera(OrthographicCamera c) {
        ortCam = c;
        return this;
    }

    public Tile getTile(){
        return tile;
    }

    public void play_walk_sound(){
        if (tile.walkSFX != null){
            walk_sound_count++;
            if (walk_sound_count % tile.walkSFX_interval == 0 | walk_sound_countdown <= 0){
                tile.walkSFX.play(VOLUME);
                walk_sound_count = 0;
                walk_sound_countdown = tile.walkSFX_interval;
//                System.out.println("sound played");
            }
//            System.out.println(walk_soundCount);
        }
    }
}
