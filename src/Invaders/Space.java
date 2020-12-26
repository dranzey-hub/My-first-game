package Invaders;


import java.awt.image.ImageObserver;
/**
 *
 * @author Link
 */
public interface Space extends ImageObserver {

    public static final int X=900;
    public static final int Y=700;
    public static final int playgroundBottom=Y-50;
    public static final int Time=10;
    public SpriteCache getSpriteCache();
    public SoundCache getSoundCache();
    public void addActor(Live a);
    public Player getPlayer();
    public void gameOver();

}
