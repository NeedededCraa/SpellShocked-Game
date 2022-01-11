package com.spellshocked.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.item.CollisionRect;
import com.spellshocked.game.world.Tile;
import com.spellshocked.game.world.World;

public class ProjectileEntity extends Entity {
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./image/Entity/Projectile/fireboltNoDiag.png"), 7, 7);
    public double damage;
    public ProjectileEntity(double dmg) {
        super(TEXTURES, 2);
        damage = dmg;
        setSize(0.1f, 0.1f);
        rect = new CollisionRect(this.getX(), this.getY(), (int)this.getWidth(), (int) this.getHeight());

    }

    @Override
    public void periodic() {
        moveToTarget2();
        boolean hit = false;
        for(Entity e : getTile().getOccupants()){
            if(e != this && !e.invincible && e instanceof SheepEntity){
                e.modifyHealth(-damage);
                hit = true;
            }
        }
        if(!isGoing || hit || (newX==getX() && newY == getAdjustedY())){
            Spellshocked.getInstance().world.removeEntity(this);
        }
        super.periodic();
    }

    public TextureRegion[] parseWalkingSheetRow(TextureRegion[] t) {
        return new TextureRegion[]{t[0], t[1], t[0], t[2]};
    }
    @Override
    public Animation<TextureRegion>[] getAnimations() {
        Animation<TextureRegion>[] a = new Animation[TEXTURES.length];
        for (int i = 0; i < TEXTURES.length; i++) {
            a[i] = new Animation<>(0.1f, parseWalkingSheetRow(TEXTURES[i]));
        }
        return a;
    }

    @Override
    public void moveToTarget() {

    }
    public void moveToTarget2(){
        super.moveToTarget();
    }
}
