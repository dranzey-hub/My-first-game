package Invaders;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
/**
 *
 * @author Link
 */
public class SoundCache extends ResourceCache {

    protected Object loadResource(URL url){
        
        return Applet.newAudioClip(url);
    }


    public AudioClip getAudioClip(String sound){

        return (AudioClip)getResource(sound);
    }


    public void playSound(final String sound){
    
            getAudioClip(sound).play();               
    }


    public void loopSound(final String sound){

            getAudioClip(sound).loop();      
    }

    public void stopSound(final String sound){

            getAudioClip(sound).stop();
    }
}
