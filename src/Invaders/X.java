package Invaders;

/**
 *
 * @author Link
 */
public class X extends Item {
    
    public boolean toxic,gotcha;
    protected byte xPlus=2;
    

    public X(Space space){
        super(space);
        setSpriteNames(new String[]{"(x)01.png","(x)02.png","(x)03.png","(x)04.png","(x)05.png","(x)06.png","(x)07.png","(x)08.png","(x)09.png",
                                    "(x)10.png","(x)11.png","(x)12.png","(x)13.png","(x)14.png","(x)15.png","(x)16.png","(x)17.png","(x)18.png",
             /*(x) :    0-34*/      "(x)19.png","(x)20.png","(x)21.png","(x)22.png","(x)23.png","(x)24.png","(x)25.png","(x)26.png","(x)27.png",
                                    "(x)28.png","(x)29.png","(x)30.png","(x)31.png","(x)32.png","(x)33.png","(x)34.png","(x)35.png","0.png",
             /*pop :   35-40*/      "1.png","2.png","3.png","4.png","5.png","x01.png","x02.png","x03.png","x04.png",
             /* x  :   41-56*/      "x05.png","x06.png","x07.png","x08.png","x09.png","x10.png","x11.png","x12.png",
                                    "x13.png","x14.png","x15.png","x16.png"});
        currentFrame=0;
        frameSpeed=4;
        Vy=3;
        toxic=true;
        count=0;
    }


@Override
   public void act(){
   localTime++;
    if(localTime%frameSpeed==0){  currentFrame=currentFrame+1;

        if(count==0)currentFrame=0;

        else if(count<80){Vy=2; if(currentFrame>34)currentFrame=0; if(count>30)frameSpeed=5;}
       
        else if(count>79){  if(toxic){currentFrame=35;frameSpeed=6;Vy=1; toxic=false;}
           if(currentFrame>56)currentFrame=41;
           if(count>90)frameSpeed=5;
           if(count>100)frameSpeed=4;
           if(count>110){frameSpeed=3;Vy=0;}
           if(count>118){frameSpeed=2;xPlus=3;}
           if(count>124){frameSpeed=1;xPlus=4;}
        }
y=y+Vy;
    }
   if(gotcha){ if(x>=841&&y<=350){ space.getPlayer().bulletTime++;this.reaper();}
               else if(x<841)x+=xPlus; if(y>200)y--; else y++;}
}


@Override
   public void collision(Live L){

    if(L instanceof Bullet){count=count+2;}
    if(L instanceof Player){if(toxic) {count++;
                                       if(space.getPlayer().y>y-height/2){
                                         if(space.getPlayer().x<x){x+=5;y-=2;} else {x-=5;y-=2;}
                                            }
                                       else{if(space.getPlayer().x<x){x+=5;y+=2;} else {x-=5;y+=2;}}
                                        }
                            else gotcha=true;}
    
}

}
