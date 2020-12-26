package Invaders;

/**
 *
 * @author Link
 */
public class Animation extends Live {
    protected int yIn;
    protected int xIn;

    public Animation(Space space, int x, int y){
        super(space);
        setSpriteNames(new String[]{"Err.png","Err.png","Err_Blink.png","Err.png","Err_right.png","Err_left.png","Err.png","Err_Blink.png","Err.png",
        "Err_wtf.png","Err.png","Err_wtf.png","Err.png","Err_wtf.png","Err.png","Err_Blink.png","Err.png","Err.png"});
        this.x=x; this.y=y;
        xIn=x; yIn=y;
        frameSpeed=40;
    }

@Override
    public void act(){
    localTime++;
      if(localTime%frameSpeed==0){
          currentFrame=(currentFrame+1)%spriteNames.length;
          
          if(currentFrame==9||currentFrame==11||currentFrame==13){
              space.getSoundCache().playSound("jump.wav");y-=height;}
          else if(currentFrame<17){y=yIn;}
          else if(currentFrame>16){  frameSpeed=33;
             space.getSoundCache().playSound("eMove2.wav"); y-=height; currentFrame=16; if(y<0){reaper();}
          }
          localTime=0;
     }

    }

}
