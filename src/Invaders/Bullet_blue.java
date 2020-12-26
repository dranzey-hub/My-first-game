package Invaders;

import java.awt.Rectangle;
/**
 *
 * @author Link
 */
public class Bullet_blue extends Bullet {

    public Bullet_blue (Space space){
        super(space);
        //setSpriteNames(new String[]{"hi_1.png","hi_2.png","hi_3.png","hi_0.png"});
        setSpriteNames(new String[]{"0_.png","1_.png","2_.png","3_.png","4_.png","5_.png"});
        frameSpeed=6;
    }


    @Override
    public Rectangle getBounds(){
        bdimage = spriteCache.getSprite(spriteNames[currentFrame]);
        width=bdimage.getWidth();
        height=bdimage.getHeight();
        return new Rectangle((int)(x),(int)(y),width,height);
    }

}
