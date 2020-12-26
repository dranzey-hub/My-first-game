package Invaders;

/**
 *
 * @author Link
 */
public class Errignignokt extends Monster {
    private double Vy=1.5;

    public Errignignokt(Space space){
        super(space);
        setSpriteNames(new String[]{"Errignignokt_01.png","Errignignokt_02.png","Errignignokt_03.png","Errignignokt_04.png","Errignignokt_05.png",
        "Errignignokt_06.png","Errignignokt_07.png","Errignignokt_08.png","Errignignokt_09.png","Errignignokt_10.png","Errignignokt_11.png",
        "Errignignokt_12.png","Errignignokt_13.png","Errignignokt_14.png","Errignignokt_15.png","Errignignokt_16.png","Errignignokt_17.png",
        "Errignignokt_18.png","Errignignokt_19.png","Errignignokt_20.png","Errignignokt_21.png","Errignignokt_22.png","Errignignokt_23.png",
        "Errignignokt_24.png"});
        attackFreq=0;
        HP=1000;
        Vx=3;
        frameSpeed=3;
    }

@Override
    public void act(){
      localTime++;
      if(localTime%frameSpeed==0){
          currentFrame=(currentFrame+1)%spriteNames.length;
          localTime=0;
      }
      if(x<1 || x>Space.X-width-1){Vx=-Vx; space.getSoundCache().playSound("eMove.wav");}
      if(y<1 || y>Space.Y-height-29){Vy=-Vy;space.getSoundCache().playSound("eMove.wav");}
      x+=Vx; y+=Vy;
}

@Override
    public void collision(Live L){
        if(L instanceof Bullet_blue){HP-=2;}
        if(L instanceof Bullet){HP--;}
        if(L instanceof Bomb)  {HP-=50;}
        if(HP<1){
             reaper();
        }
}

}
