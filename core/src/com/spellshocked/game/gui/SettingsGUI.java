package com.spellshocked.game.gui;

import com.spellshocked.game.Spellshocked;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.scenes.scene2d.Event;
        import com.badlogic.gdx.scenes.scene2d.EventListener;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.ui.*;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.spellshocked.game.Spellshocked;
import com.spellshocked.game.gui.GUI;
import com.spellshocked.game.world.World;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
        import com.sun.tools.javac.comp.Check;

public class SettingsGUI extends GUI {
        public static final String SKIN="./pixthulhu/skin/pixthulhu-ui.json";
        public SettingsGUI(Spellshocked g){
        super(SKIN);
//volume
        final Slider volumeMusicSlider = new Slider(0f,1f,0.1f,false,skin);
        volumeMusicSlider.setValue(g.getPreferences().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener(){
        @Override
        public boolean handle(Event event){
        g.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
        return false;
        }
        });
        volumeMusicSlider.setSize((Gdx.graphics.getWidth()/1.33f),(Gdx.graphics.getHeight()/4.8f));
        volumeMusicSlider.setPosition((Gdx.graphics.getWidth()/8f),(Gdx.graphics.getHeight()/3.42f));
        Label volumeMusicSliderLabel = new Label("MusicVolume",skin);
        volumeMusicSliderLabel.setSize((Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f));
        volumeMusicSliderLabel.setPosition((Gdx.graphics.getWidth()/2.1f),(Gdx.graphics.getHeight()/3.5f));

        addActor(volumeMusicSlider);
        addActor(volumeMusicSliderLabel);

//music
/*finalCheckBoxmusicCheckbox=newCheckBox(null,skin);
musicCheckbox.setChecked(g.getPreferences().isMusicEnabled());
musicCheckbox.addListener(newEventListener(){
@Override
publicbooleanhandle(Eventevent){
booleanenabled=musicCheckbox.isChecked();
g.getPreferences().setMusicEnabled(enabled);
returnfalse;
}
});
musicCheckbox.setSize((Gdx.graphics.getWidth()/1.33f),(Gdx.graphics.getHeight()/4.8f));
musicCheckbox.setPosition((Gdx.graphics.getWidth()/15f),(Gdx.graphics.getHeight()/1.84f));
LabelmusicCheckboxLabel=newLabel("Music",skin);
musicCheckboxLabel.setSize((Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f));
musicCheckboxLabel.setPosition((Gdx.graphics.getWidth()/4f),(Gdx.graphics.getHeight()/1.7f));

addActor(musicCheckboxLabel);
addActor(musicCheckbox);
*/
        final CheckBox soundEffects = new CheckBox(null,skin);
        soundEffects.setChecked(g.getPreferences().isSoundEffectsEnabled());
        soundEffects.addListener(new EventListener(){
    @Override
        public boolean handle(Event event){
        boolean enabled=soundEffects.isChecked();
        g.getPreferences().setSoundEffectsEnabled(enabled);
        return false;
        }
        });
        soundEffects.setSize((Gdx.graphics.getWidth()/1.33f),(Gdx.graphics.getHeight()/4.8f));
        soundEffects.setPosition((Gdx.graphics.getWidth()/2.5f),(Gdx.graphics.getHeight()/1.84f));
        Label soundEffectsLabel = new Label("SoundEffects",skin);
        soundEffectsLabel.setSize((Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f));
        soundEffectsLabel.setPosition((Gdx.graphics.getWidth()/1.5f),(Gdx.graphics.getHeight()/1.7f));

        addActor(soundEffectsLabel);
        addActor(soundEffects);

//initalizestageandallyourbuttons
        final CheckBox wasd = new CheckBox(null,skin);
        wasd.setChecked(g.getPreferences().isWasdEnabled());
        soundEffects.addListener(new EventListener(){
    @Override
    public boolean handle(Event event){
        boolean enabled=wasd.isChecked();
        g.getPreferences().setWasdEnabled(enabled);
        return false;
        }
        });
        wasd.setSize((Gdx.graphics.getWidth()/1.33f),(Gdx.graphics.getHeight()/4.8f));
        wasd.setPosition((Gdx.graphics.getWidth()/2.5f),(Gdx.graphics.getHeight()/1.5f));
        Label wasdLabel = new Label("WASD",skin);
        wasdLabel.setSize((Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f));
        wasdLabel.setPosition((Gdx.graphics.getWidth()/1.7f),(Gdx.graphics.getHeight()/1.5f));

        addActor(wasdLabel);
        addActor(wasd);

        final CheckBox keys = new CheckBox(null,skin);
        keys.setChecked(g.getPreferences().isArrowKeysEnabled());
        keys.addListener(new EventListener(){
@Override
public boolean handle(Event event){
        boolean enabled=keys.isChecked();
        g.getPreferences().setArrowKeysEnabled(enabled);
        return false;
        }
        });
        keys.setSize((Gdx.graphics.getWidth()/1.33f),(Gdx.graphics.getHeight()/4.8f));
        keys.setPosition((Gdx.graphics.getWidth()/7f),(Gdx.graphics.getHeight()/1.5f));
        Label keysLabel = new Label("KEYS",skin);
        keysLabel.setSize((Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/10f));
        keysLabel.setPosition((Gdx.graphics.getWidth()/10f),(Gdx.graphics.getHeight()/1.5f));

        addActor(keysLabel);
        addActor(keys);

        ButtonGroup buttonGroup= new ButtonGroup(wasd,keys);
//nextsetthemaxandminamounttobechecked
        buttonGroup.setMaxCheckCount(1);

//itmaybeusefultousethismethod:
//buttonGroup.setUncheckLast(true);

        TextButton back = new TextButton("Back",skin);
        back.addListener(new ClickListener(){
@Override
public void clicked(InputEvent event,float x,float y){
        g.setScreen(g.titleGUI);
        }
        });

        back.setSize((Gdx.graphics.getWidth()/2.9f),(Gdx.graphics.getHeight()/4.8f));
        back.setPosition((Gdx.graphics.getWidth()/1.88f),(Gdx.graphics.getHeight()/24f));
        addActor(back);
        }
}