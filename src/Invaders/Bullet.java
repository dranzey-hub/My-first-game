package Invaders;

import java.awt.Rectangle;
/**
 *
 * @author Link
 */
public class Bullet extends Live {
    protected static float bulletSpeed=6.5f;

    public Bullet(Space space){
        super(space);
        setSpriteNames(new String[]{"shoot.png"});
    }

@Override
    public void act(){
        super.act();
        y=y-bulletSpeed;
        if(y<-120){this.reaper(); space.getPlayer().max= space.getPlayer().max + 1;}
    }
@Override
    public void collision(Live L){
        //if(L instanceof Player || L instanceof Item || L instanceof Bullet){}
        if(L instanceof Monster || L instanceof Ignignokt || L instanceof Spit || L instanceof X){
          reaper();
          space.getPlayer().max= space.getPlayer().max + 1;
        }
    }
@Override
    public Rectangle getBounds(){
        bdimage = spriteCache.getSprite(spriteNames[currentFrame]);
        width=bdimage.getWidth();
        height=bdimage.getHeight();
        return new Rectangle((int)(x)+1,(int)(y)+2,width-1,height-4);
}

}
