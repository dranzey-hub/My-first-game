package Invaders;

import java.awt.Graphics2D;
/**
 *
 * @author Link
 */
public class Flame extends Live {


     public Flame(Space space){
         super(space);
         setSpriteNames(new String[]{"flameblue_0.png",  "flameblue_1.png",
                                     "flameblueDn_0.png","flameblueDn_1.png",
                                     "flameblueUp_0.png","flameblueUp_1.png"});
     }


@Override
    public void paint(Graphics2D g){
      if(!space.getPlayer().hit){
        bdimage = spriteCache.getSprite(spriteNames[currentFrame]);
        g.drawImage(bdimage,(int)x,(int)y,space); }
}

     
@Override
    public void act(){
       localTime++;
       if(localTime%frameSpeed==0){
        
           //pos
           if(space.getPlayer().right){
         x=space.getPlayer().x+space.getPlayer().width/2-width/2+space.getPlayer().currentFrame/2;    if(space.getPlayer().currentFrame>2)x+=1;
         y=space.getPlayer().y+2*space.getPlayer().height/3;
         if(space.getPlayer().currentFrame==space.getPlayer().spriteNames.length-1){x+=1;}
            }
           else if(space.getPlayer().left){
         x=space.getPlayer().x+space.getPlayer().width/2-width/2-6+space.getPlayer().currentFrame   -1;
         y=space.getPlayer().y+2*space.getPlayer().height/3;
            }
           else{
         x=space.getPlayer().x+space.getPlayer().width/2-width/2+space.getPlayer().currentFrame -5;
         y=space.getPlayer().y+2*space.getPlayer().height/3;
            }


           if(space.getPlayer().spun){x=space.getPlayer().x+space.getPlayer().width/2 - width/2;}


                //FLAME STATE
           if(space.getPlayer().up){
               currentFrame=(currentFrame+1)%spriteNames.length;
               if(currentFrame<4){currentFrame=4;}
           }
           else if(space.getPlayer().down){
               currentFrame=(currentFrame+1)%4;
               if(currentFrame<2){currentFrame=2;}
           }
           else{currentFrame=(currentFrame+1)%2;}


         
        

           localTime=0;
       }                         ///////POSITION
       //if(space.getPlayer().hit){
         //x=space.getPlayer().x+space.getPlayer().width/2-width/2;    //this one for the scan-shield
         //y=space.getPlayer().y+2*space.getPlayer().height/3;}

       
}

}
