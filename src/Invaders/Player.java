package Invaders;


import java.awt.event.KeyEvent;
import java.awt.Rectangle;
/**
 *
 * @author Link
 */
public class Player extends Live {
    public static final int MAX_SHIELD=200;
    public static final int MAX_BOMBS=5;
    private float playerSpeed, playerSpeedDiag;
    private float Vx,Vy;
    protected boolean up,down,right,left,
                      hit,start,manualShield,hitbyx,delay,
                      leftSpin0,leftSpin1,rightSpin0,rightSpin1,spun;
    protected byte bulletTime;
    protected int bombs,score,shield,recharge,laser,max,timedstrtn;
    private int u,r,l,d;
    private int count2,count3,countdelay,spinCount,spinLocalTime;


    public Player(Space space){
        super(space);
        setSpriteNames(new String[]{"arwingL4.png","arwingL3.png","arwingL2.png","arwingL1.png","arwingL0.png",
        "arwing.png","arwingR0.png","arwingR1.png","arwingR2.png","arwingR3.png","arwingR4.png",
        "arwing_spin01.png","arwing_spin02.png","arwing_spin03.png","arwing_spin04.png","arwing_spin05.png",   //SPIN(11-21)
        "arwing_spin06.png","arwing_spin07.png","arwing_spin08.png","arwing_spin09.png","arwing_spin10.png","arwing_spin11.png"});
     
        laser=0;
        bombs=5;
        shield=MAX_SHIELD;
        score=0;
        max=8;
        recharge=0;
        playerSpeed=2;
        playerSpeedDiag=playerSpeed/(float)Math.sqrt(2);
        frameSpeed=4;
        currentFrame=5;
        count=0;count2=0;
        bulletTime=6;
        timedstrtn=45;
    }

    protected void updateSpeed(){

        if(up)         {d=-1;Vy=-playerSpeed;u=17;
                        if(right){l=-1;Vx=playerSpeed;r=14;}
                        else if(left){r=-1;Vx=-playerSpeed;l=14;}}
        else if(down)  {u=-1;Vy=playerSpeed;d=14;
                        if(right){l=-1;Vx=playerSpeed;r=14;}
                        else if(left){r=-1;Vx=-playerSpeed;l=14;}}
        else if(right) {l=-1;Vx=playerSpeed;r=14;
                        if(up){d=-1;Vy=-playerSpeed;u=14;}
                        else if(down){u=-1;Vy=playerSpeed;d=14;}}
        else if(left)  {r=-1;Vx=-playerSpeed;l=14;
                        if(up){d=-1;Vy=-playerSpeed;u=14;}
                        else if(down){u=-1;Vy=playerSpeed;d=14;}}  
        //Equitative 8 direction movement
       /* if(up)         {d=-1;u=17;
                        if(right){l=-1;Vx=playerSpeedDiag;Vy=-playerSpeedDiag;r=14;}
                        else if(left){r=-1;Vx=-playerSpeedDiag;Vy=-playerSpeedDiag;l=14;}
                        else Vy=-playerSpeed;}
        else if(down)  {u=-1;d=14;
                        if(right){l=-1;Vx=playerSpeedDiag;Vy=playerSpeedDiag;r=14;}
                        else if(left){r=-1;Vx=-playerSpeedDiag;Vy=playerSpeedDiag;l=14;}
                        else Vy=playerSpeed;}
        else if(right) {l=-1;r=14;
                        if(up){d=-1;Vy=-playerSpeedDiag;Vx=playerSpeedDiag;u=14;}
                        else if(down){u=-1;Vy=playerSpeedDiag;Vx=playerSpeedDiag;d=14;}
                        else Vx=playerSpeed;}
        else if(left)  {r=-1;l=14;
                        if(up){d=-1;Vy=-playerSpeedDiag;Vx=-playerSpeedDiag;u=14;}
                        else if(down){u=-1;Vy=playerSpeed;Vx=-playerSpeedDiag;d=14;}
                        else Vx=-playerSpeed;}  */
                                                                      //MOMENTUM
        if(u>0&&u<9)Vy=-1;if(u==0)Vy=0;if(r>0&&r<9)Vx=1;if(r==0)Vx=0;if(l>0&&l<9)Vx=-1;if(l==0)Vx=0;if(d>0&&d<9)Vy=1;if(d==0)Vy=0;
        r--;l--;u--;d--;
    }

     public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:    up=true;resetSpin();break;
            case KeyEvent.VK_DOWN:  down=true;resetSpin();break;
            case KeyEvent.VK_RIGHT: right=true;leftSpin0=false;if(rightSpin0)rightSpin1=true;spinCount++;break;
            case KeyEvent.VK_LEFT:  left=true;rightSpin0=false;if(leftSpin0) leftSpin1=true;spinCount++;break;
            case KeyEvent.VK_SPACE: if(!manualShield) fire();break;
            case KeyEvent.VK_B: fireBomb();break;
            case KeyEvent.VK_T: delay=true;hitbyx=true;break;
            case KeyEvent.VK_S: if(!hit) manualShield=true;break;
            
            
            //case KeyEvent.VK_ENTER: start=true;break;
        }
        //updateSpeed();
    }
     public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:    up=false;break;
            case KeyEvent.VK_DOWN:  down=false;break;
            case KeyEvent.VK_RIGHT: right=false; if(spinCount<2)rightSpin0=true;spinCount=0;break;
            case KeyEvent.VK_LEFT:  left=false;  if(spinCount<2) leftSpin0=true;spinCount=0;break;
            case KeyEvent.VK_S: manualShield=false;break;
        }
        //updateSpeed();
    }
     public void fire(){

         if(max>0 && !hit){

            Bullet bulletL = new Bullet(space);
            Bullet bulletR = new Bullet(space);

            if(laser==1){
                bulletL = new Bullet_blue(space);
                bulletR = new Bullet_blue(space);
            }                                         //OFFSET CORRECTION
            bulletL.setx(x+(width/3)-bulletL.getWidth()    -3);
            bulletR.setx(x+((2*width)/3)-bulletR.getWidth()-4);
            bulletL.sety(y); bulletR.sety(y);
            space.getSoundCache().stopSound("shoot.wav");
            space.addActor(bulletL);
            space.addActor(bulletR);
            space.getSoundCache().playSound("shoot.wav");
            max=max-2;
         
         }
     }
     public void fireBomb(){
         
         if(bombs>0 && recharge<1){ recharge=180;
             space.getSoundCache().playSound("explosion.wav");
             space.addActor(new Bomb(space,Bomb.U,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.D,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.R,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.L,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.UR,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.UL,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.DR,x+width/2-15,y+5));
             space.addActor(new Bomb(space,Bomb.DL,x+width/2-15,y+5));
             bombs--;
         }
     }

     public void bulletTime(){

         
         if(bulletTime>0){


             
             TimeWave wave = new TimeWave(space);
             wave.x=x-375+width/2;
             wave.y=y-375+height/2 +6;
             space.addActor(wave);
             Invaders.time=timedstrtn;
             bulletTime--;
             
             
         }
     }



@Override
    public void act(){

        updateSpeed();

        if(shield<0){
                space.gameOver();
            }


        if(leftSpin0||rightSpin0){spinCount++;if(spinCount>8){spinCount=0;leftSpin0=rightSpin0=false;}}
        if(leftSpin1)leftSpin();   if(rightSpin1)rightSpin();

        if(hitbyx){
               up=false;right=false;left=false;down=false;hitbyx=false;
                   }
        if(delay){
            if(Invaders.time==space.Time){countdelay++;if(countdelay>10){delay=false;countdelay=0;count3=0;  bulletTime();}}
            if(Invaders.time==timedstrtn){countdelay++;if(countdelay>6) {delay=false;countdelay=0;count3=0;  bulletTime();}}
        }




        localTime++;
        if(localTime%frameSpeed==0){


            /*if(hit){
                currentFrame++;               OFFSET FOR THIS: (7,3)
                if(currentFrame>23){currentFrame=5;frameSpeed=3;hit=false;x=x+7;y=y+3;}
            }*/
            if(hit){
              count2++;
              if(count2>9){count2=0;hit=false;}
            }

            

        if(!spun){
            if(left){
                if(currentFrame>0){
                    currentFrame--;
                } else currentFrame=0;
            }
            else if(right){
                if(currentFrame<10){
                    currentFrame++;
                } else currentFrame=10;
            }
            else if(currentFrame>5){
                currentFrame--;
            }
            else if(currentFrame<5){
                currentFrame++;
            }
         }
            

            localTime=0;
        }




        if(Invaders.time==timedstrtn){
            count3++;
            if(count3<50){
                playerSpeed = 1.25f;
                playerSpeedDiag=playerSpeed/(float)Math.sqrt(2);}
            else if(count3>49&&count3<110){
                playerSpeed = 2;
                playerSpeedDiag=playerSpeed/(float)Math.sqrt(2);}
            else if(count3>109&&count3<350){
                playerSpeed = 4f;
                playerSpeedDiag=playerSpeed/(float)Math.sqrt(2);}
            else if(count3>349&&count3<400)
                {playerSpeed = 1.5f;playerSpeedDiag=playerSpeed/(float)Math.sqrt(2);Bullet.bulletSpeed=3;}
            else {Invaders.time=space.Time;playerSpeed=2;playerSpeedDiag=playerSpeed/(float)Math.sqrt(2);Bullet.bulletSpeed=6.5f;
            count3=0;}
             }


        x=x+Vx;
        y=y+Vy;
        if(x<0){x=0;}
        if(x>Space.X-width-4){x=Space.X-width-4;}
        if(y<0){y=0;}
        if(y>Space.playgroundBottom-50){y=Space.playgroundBottom-50;}


    }




@Override
public Rectangle getBounds(){
        bdimage = spriteCache.getSprite(spriteNames[currentFrame]);
        width=bdimage.getWidth();
        height=bdimage.getHeight();
        R=new Rectangle((int)(x)+2,(int)(y)+2,width-4,height-4);return R;
    }


@Override
    public void collision(Live L){

        if(L instanceof Spit){
            if(!hit){ hit=true;
               if(Math.random()<0.5){currentFrame=9;x+=5;y+=2;} else {currentFrame=1;x-=5;y+=2;}
            }
            Spit S = (Spit)L;
            setShield(S.getDamage());
            L.reaper();
        }
        if(L instanceof Ignignokt){ if(!hit){hit=true;}
            shield-=1;
        }
        else if(L instanceof Errignignokt){ if(!hit){hit=true;if(Math.random()<0.5){currentFrame=9;x+=5;y+=2;} else {currentFrame=1;x-=5;y+=2;}}
            shield-=2;
        }
        else if(L instanceof Monster){ if(!hit){hit=true;}
            count++;
            shield-=4;
            if(count>5){L.reaper();count=0;}
        }       
        if(L instanceof RingG){
            bombs=MAX_BOMBS;
            shield+=1;
            if(shield>MAX_SHIELD){shield=MAX_SHIELD;}
            }
        if(L instanceof Ring){
            shield+=1;
            if(shield>MAX_SHIELD){shield=MAX_SHIELD;}
        }
        if(L instanceof BoostLaser){
            count++;if(count>90)laser=1;
        }
        if(L instanceof X){
            X unknwn= (X)L;
            if (unknwn.toxic){ hit=true;hitbyx=true;shield-=10;       //MODIFY THIS SIL VOUS PLAIT  **********
            if((x>=unknwn.x&&x<unknwn.x+10)||(x<=unknwn.x&&x>unknwn.x-10)){y+=95;unknwn.y-=30;} else if(x<unknwn.x&&y>unknwn.y+30){x-=30;y+=30;currentFrame=0;} else if(x<unknwn.x&&y<=unknwn.y+30){x-=30;y-=20;currentFrame=0;}
            else if(x>unknwn.x&&y>unknwn.y+30){x+=30;y+=30;currentFrame=10;}else if(x>unknwn.x&&y<=unknwn.y+30){x+=30;y-=20;currentFrame=10;} }
            
        }


}

    public void leftSpin(){
        if(!spun)currentFrame=11;
        spun=true;
        if(currentFrame<21&&currentFrame>10) {
            x-=2;spinLocalTime++;
            if(spinLocalTime%frameSpeed==0){currentFrame++;spinLocalTime=0;}
        }else{leftSpin0=leftSpin1=spun=false;currentFrame=5;}
    }
    public void rightSpin(){
        if(!spun)currentFrame=21;
        spun=true;
        if(currentFrame>11) {
            x+=2;spinLocalTime++;
            if(spinLocalTime%frameSpeed==0){currentFrame--;spinLocalTime=0;}
        }else{rightSpin0=rightSpin1=spun=false;currentFrame=5;}
    }

    public void resetSpin(){
        leftSpin0=false; rightSpin0=false;
        spinCount=0;
    }


public void setBombs(int i){
    bombs+=i;
}
public void setScore(int i){
    score+=i;
}
public void setShield(int i){
    shield+=i;
}




}
