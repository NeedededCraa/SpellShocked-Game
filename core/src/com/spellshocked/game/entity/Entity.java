package com.spellshocked.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    /**
     * must use set_walk_boundary()
     */
    private float xMin, xMax, yMin, yMax;

    private Tile tile;

    private TextureRegion[][] textures;


    private Direction lastDirection;
    public Direction getLastDirection() {
        return lastDirection;
    }

    private State lastAction;
    private float stateTime;


    float currentTileZ = 0;
    static final float TOLERANCE_ZONE = 0.2f;

    public float VOLUME;
    public int walk_sound_count;

    protected TextureRegion tex;


    public Entity(TextureRegion[][] t) {
        this(t, 1);
    }

    public Entity(TextureRegion[][] t, float walkSpeed) {
        setRegion(t[0][0]);
        setWalkSpeed(walkSpeed);
        setScale(100);
        setSize(16, 24);
        textures = t;

        lastAction = State.IDLE;
        lastDirection = Direction.UP;
        stateTime = 0f;
    }
 
    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    private float newX = -1, newY = -1;

    public void move(Direction dir) {
        TextureRegion t = null;
        if (dir != Direction.NONE) {
            lastAction = State.MOVING;
            if(getAnimations()!= null) t = getAnimations()[dir.index].getKeyFrame(stateTime, true);
            lastDirection = dir;
            float x = newX+ walkSpeed *dir.xMod, y = newY+ walkSpeed *dir.yMod;
            if(xMax > x && x > xMin && (((x+9)%16 > walkSpeed || tile.left.isStandable() || x>newX) && ((x+9)%16 < 16- walkSpeed || tile.right.isStandable() || x<newX))) newX = x;
            if(yMax > y && y > yMin && (((y+3)%12 > walkSpeed || tile.front.isStandable() || y>newY) && ((y+3)%12 < 12- walkSpeed || tile.back.isStandable() || y<newY))) newY = y;
        } else {
            lastAction = State.IDLE;
            t = textures[3][lastDirection.index];
        }
        if(t != null) setRegion(t);
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
        moveToTarget();

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

    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void setTile(Tile i) {
        tile = i;
    }

    public float getTerrainHeight() {
        return tile!=null ? tile.zValue : 0;
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
        if(health <= 0) onDeath();
    }
    public void onDeath(){

    }
    public void setHealth(double value){
        health = value;
    }

    public void setOrthographicCamera(OrthographicCamera c) {
        ortCam = c;
    }

    public Tile getTile(){
        return tile;
    }

    public void play_walk_sound() {
        if (tile.walkSFX_type1 != null) { //in case the sound didn't initialize properly
            if (walk_sound_count == tile.walkSFX_type1_interval) {
                tile.walkSFX_type1.play(VOLUME);
//                System.out.println("sound 1 played");
            } else if (walk_sound_count == tile.walkSFX_type1_interval + tile.walkSFX_type2_interval) {
                tile.walkSFX_type2.play(VOLUME);
                walk_sound_count = 0;
//                System.out.println("sound 2 played");
            } else if (walk_sound_count > tile.walkSFX_type1_interval + tile.walkSFX_type2_interval){
                walk_sound_count = 0; // in case overflowed
            }
            walk_sound_count++;
//            System.out.println(walk_sound_count);
        }
    }
    public Animation<TextureRegion>[] getAnimations(){
        return null;
    }
    boolean isGoing;
    float targetX, targetY;
    public void targetTile(Tile tile){
        if(tile == null) return;
        targetX = tile.xValue;
        targetY = tile.yValue;
    }
    public void startMoving(){
        isGoing = true;
    }
    float incX = 0, incY = 0, adj = 1;
    public void moveToTarget(){
        if(!isGoing) return;
        float nX = targetX-tile.xValue, nY = targetY-tile.yValue;
        incX+=nX/Math.max(0.01, Math.abs(nY));
        incY+=nY/Math.max(0.01, Math.abs(nX));

        adj = 1;

        Direction d;
        if(Math.abs(nY) > Math.abs(nX)){
            d = nY > 0 ? Direction.UP : Direction.DOWN;
            adj = Math.abs(walkSpeed/incY);

        } else {
            d = nX > 0 ? Direction.RIGHT : Direction.LEFT;
            adj = Math.abs(walkSpeed/incX);
        }

        if(getAnimations()!= null) setRegion(getAnimations()[d.index].getKeyFrame(stateTime, true));


        float x = newX+ incX*adj*walkSpeed, y = newY+ incY*adj*walkSpeed;
        if(xMax > x && x > xMin && (((x+9)%16 > walkSpeed || tile.left.isStandable() || x>newX) && ((x+9)%16 < 16- walkSpeed || tile.right.isStandable() || x<newX))) newX = x;
        if(yMax > y && y > yMin && (((y+3)%12 > walkSpeed || tile.front.isStandable() || y>newY) && ((y+3)%12 < 12- walkSpeed || tile.back.isStandable() || y<newY))) newY = y;



        if(Math.abs(incX) >= walkSpeed) incX = 0;
        if(Math.abs(incY) >= walkSpeed) incY = 0;
        if(Math.abs(nX)+Math.abs(nY) ==0){
            isGoing = false;
            incX = 0;
            incY = 0;
        }
    }

    public boolean isAtTarget(Entity other){
        return other.getTile() == getTile();
    }

    public void set_walk_boundary(String mode, int Tile_X, int Tile_Y){
        if (mode.equals("Tile")){
            xMin = 0;
            xMax = Tile_X*16;
            yMin = 0;
            yMax = Tile_Y*12;
        }
    }

    public void dispose(){
//        walk_sound_count = 0;
//        for (TextureRegion[] TextureRegion_row: textures){
//            for (TextureRegion TextureRegion_single: TextureRegion_row){
//                TextureRegion_single.getTexture().dispose();
//            }
//        }
    }
}
