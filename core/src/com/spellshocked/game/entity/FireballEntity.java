package com.spellshocked.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.item.CollisionRect;

import java.util.Set;

public class FireballEntity extends ProjectileEntity {
    public static final TextureRegion[][] TEXTURES = TextureRegion.split(new Texture("./image/Entity/Projectile/fireboltNoDiag.png"), 7, 7);
    long time;

    public FireballEntity() {
        super(2, TEXTURES);
        setSize(0.1f, 0.1f);
        rect = new CollisionRect(this.getX()*4, this.getY()*4, (int)this.getWidth()*4, (int) this.getHeight()*4);
        time = System.currentTimeMillis();
        super.SFX_type1 = Gdx.audio.newSound(Gdx.files.internal("./audio/Entity/Projectile/fireball.1.60.ogg"));
    }

    @Override
    public void periodic() {
        moveToTarget2();
        boolean hit = false;
        for(Entity e : Spellshocked.getInstance().world.allEntitiesNear(getTile(), 2)){
            if(e != this && !e.invincible && e instanceof Hostile){
                e.modifyHealth(-damage/getTile().distanceFrom(e.getTile()));
                hit = true;
            }
        }
        if(!isGoing || hit || (newX==getX() && newY == getAdjustedY())){
            Spellshocked.getInstance().world.replaceEntity(this, new ExplosionEntity());
        }
        if (time+2000<System.currentTimeMillis()) Spellshocked.getInstance().world.removeEntity(this);
        super.periodic();
    }

    @Override
    public void moveToTarget() {
    }
    public void moveToTarget2(){
        super.moveToTarget();
    }
}
