package Invaders;

/**
 *
 * @author Link
 */
public class Item extends Live {

    protected int Vy;
    protected boolean anima=false;


    public Item(Space space){

        super(space);
        frameSpeed=4;

    }

@Override
    public void act(){
        localTime++;

        if(localTime%frameSpeed==0){

              currentFrame=(currentFrame+1)%spriteNames.length;
              if(anima){
                 anima();
              }
          localTime=0;
     }

      y=y+Vy;
    }

@Override
    public void collision(Live L){
        if(L instanceof Player){
            anima=true;
        }
}

    public void anima(){
        Vy=0;
        x=space.getPlayer().x+(space.getPlayer().getWidth ()/2)-(width/2);
        y=space.getPlayer().y+(space.getPlayer().getHeight()/2)-(height/2) +5;
        frameSpeed=1;
        count++;
        if(count>99){reaper();}
    }


}
