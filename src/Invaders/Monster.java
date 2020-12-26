package Invaders;
/**
 *
 * @author Link
 */
public class Monster extends Live {
    protected double attackFreq=0.002;
    protected int Vx;
    protected int HP;

    
    public int getVx(){return Vx;}
    public void setVx(int Vx){this.Vx=Vx;}

    public Monster(Space space){
        super(space);
        setSpriteNames(new String[]{"Err.png","Err_Blink.png"});
        HP=4;
        setFrameSpeed(45);
    }

    public void spit(){
        space.getSoundCache().playSound("eSpit.wav");
        Spit S = new Spit(space,3,-10,0);
        S.setx(x+width/2);
        S.sety(y+height/2);
        space.addActor(S);
    }
@Override
    public void act(){                  if(y>Space.Y){space.gameOver();}//MOONINITES WIN!!!
        localTime++;

        if(localTime%frameSpeed==0){

            space.getSoundCache().playSound("eMove.wav");
            double blink = Math.random();
            if(blink>.8){
                currentFrame=1;
                localTime=0;
            }
            else {currentFrame=0;
                  localTime=0;}

        if(x<0 || x>Space.X-width){

            x=x-Vx; Vx=-Vx; y=y+2*height;
        }
            x=x+Vx;
            
        }
        
        if(Math.random()<attackFreq){
            spit();
        }
    }
@Override
    public void collision(Live L){
       if(L instanceof Bullet_blue){HP-=2;}
       if(L instanceof Bullet){HP--;}
       if(L instanceof Bomb)  {HP-=50;}
       if(HP<1){
             reaper();
             space.getPlayer().setScore(10);
            }
        }
 
}
