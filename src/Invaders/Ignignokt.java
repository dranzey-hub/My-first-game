package Invaders;

/**
 *
 * @author Link
 */
public class Ignignokt extends Monster {
    protected boolean spin;
 

    public Ignignokt(Space space,boolean spin){
        super(space);
        HP=100;
        setSpriteNames(new String[]{"Ignignokt.png","Ignignokt_Blink.png",
        "IgnignoktGlowing_01.png","IgnignoktGlowing_02.png","IgnignoktGlowing_03.png","IgnignoktGlowing_04.png",
        "IgnignoktGlowing_05.png","IgnignoktGlowing_06.png","IgnignoktGlowing_07.png","IgnignoktGlowing_08.png",
        "IgnignoktGlowing_09.png","IgnignoktGlowing_10.png","IgnignoktGlowing_11.png","IgnignoktGlowing_12.png",
        "IgnignoktGlowing_13.png","IgnignoktGlowing_14.png","IgnignoktGlowing_15.png","IgnignoktGlowing_16.png",
        "IgnignoktGlowing_17.png","IgnignoktGlowing_18.png","IgnignoktGlowing_19.png","IgnignoktGlowing_20.png",
        "IgnignoktGlowing_21.png","IgnignoktGlowing_22.png","IgnignoktGlowing_23.png","IgnignoktGlowing_24.png"});
        attackFreq=.001;
        this.spin=spin;
        if(spin){Vx=2; frameSpeed=3; HP=300; attackFreq=0;}
    }

    @Override
    public void act(){                  if(y>Space.Y){space.gameOver();}//MOONINITES WIN!!!
        localTime++;
        if(localTime%frameSpeed==0){

            if(!spin){
              space.getSoundCache().playSound("iMove.wav");
              double blink = Math.random();
              if(blink>.8){
                currentFrame=1;
              }
              else currentFrame=0;
            }
            if(spin){
                currentFrame=(currentFrame+1)%spriteNames.length;
                if(currentFrame<2){currentFrame=2;}
            }

        if(x<0 || x>Space.X-width){

            x=x-Vx; Vx=-Vx; y=y+2*height/3;
        }
            x=x+Vx;
            
            localTime=0;
        }
        if(Math.random()<attackFreq){
            spit();
        }
    }

@Override
    public void spit(){
        space.getSoundCache().playSound("iSpit.wav");
        Spit S = new Spit(space,2,-40,1);
        S.setx(x+width/2-S.getWidth());
        S.sety(y+height/2-S.getHeight());
        space.addActor(S);
    }
@Override
    public void collision(Live L){
        if(L instanceof Bullet_blue){HP-=2;}
        if(L instanceof Bullet){HP--;}
        if(L instanceof Bomb)  {HP-=50;}
        if(HP<1){
             reaper();
             space.getPlayer().setScore(100);
            }
}

public void setSpin(boolean b){
    this.spin=b;
}

}
