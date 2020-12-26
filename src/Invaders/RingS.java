/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Invaders;

/**
 *
 * @author Link
 */
public class RingS extends Ring {

    public RingS(Space space){

        super(space);
        setSpriteNames(new String[]{"ringS_0.png","ringS_1.png","ringS_2.png","ringS_3.png","ringS_4.png",
        "ringS_5.png","ringS_6.png","ringS_7.png","ringS_8.png","ringS_9.png","ringS_10.png","ringS_11.png"});
        
    }

@Override
public void anima(){
        Vy=0;
        x=space.getPlayer().x+(space.getPlayer().getWidth ()/2)-(width/2);
        y=space.getPlayer().y+(space.getPlayer().getHeight()/2)-(height/2);
        frameSpeed=1;
        count++;
        if(count>49){reaper();}
    }

}
