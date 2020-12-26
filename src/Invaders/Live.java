package Invaders;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Link
 */
public class Live {

    protected int width,height,currentFrame,frameSpeed,localTime,count;
    protected float x,y;
    protected String[] spriteNames;
    protected Space space;
    protected SpriteCache spriteCache;
    protected boolean mark;
    protected BufferedImage bdimage;

    public Live(Space space){
        this.space=space;
        spriteCache = space.getSpriteCache();
        currentFrame=0;
        frameSpeed=1;
        localTime=0;
    }
Rectangle R;
    public Rectangle getBounds(){
        bdimage = spriteCache.getSprite(spriteNames[currentFrame]);
        width=bdimage.getWidth();
        height=bdimage.getHeight();
        R= new Rectangle((int)(x),(int)(y),width,height); return R;
    }

    public void paint(Graphics2D g){
        bdimage = spriteCache.getSprite(spriteNames[currentFrame]);
        //if(R != null){g.setColor(Color.white);g.fill(R);}
        g.drawImage(bdimage,(int)x,(int)y,space);
    }

    public void setx(float f){
        this.x=f;
    }   
    public void sety(float f){
        this.y=f;
    }
    public void setFrameSpeed(int i){
        this.frameSpeed=i;
    }
       
    public void setSpriteNames(String names[]){
        spriteNames = names;
        for(int i=0;i<names.length;i++){
        spriteCache.getSprite(spriteNames[i]);
        }
    }

    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }

        
    public void reaper(){
        mark=true;
    }
    public boolean isMarked(){
        return mark;
    }
///////////////////FOR LINEAR ANIMATION
    public void act(){
      localTime++;
      if(localTime%frameSpeed==0){
          currentFrame=(currentFrame+1)%spriteNames.length;
          localTime=0;
     }
    }


    public void collision(Live L){}

}
