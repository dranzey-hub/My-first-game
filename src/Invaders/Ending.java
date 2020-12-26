package Invaders;

/**
 *
 * @author Link
 */
public class Ending extends Live {

    public Ending(Space space){
        super(space);
        setSpriteNames(new String[]{"mOONRlz_00.png","mOONRlz_01.png","mOONRlz_02.png","mOONRlz_03.png",
                                    "mOONRlz_04.png","mOONRlz_05.png","mOONRlz_06.png","mOONRlz_07.png"});
        frameSpeed=90;
        count=0;
    }

    
@Override
   public void act(){
    localTime++;count++;
      if(localTime%frameSpeed==0 && count<800){
          currentFrame=(currentFrame+1)%spriteNames.length; if(currentFrame>2)space.getSoundCache().playSound("finger.wav");if(currentFrame==2)frameSpeed=110;
          localTime=0;
     }
}

}
