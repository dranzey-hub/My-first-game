package Invaders;

/**
 *
 * @author Link
 */
public class BoostLaser extends Item {

    public BoostLaser(Space space){
        super(space);
        setSpriteNames(new String[]{"blueBoost1.png","blueBoost2.png","blueBoost3.png","blueBoost4.png","blueBoost5.png",
        "blueBoost6.png","blueBoost7.png","blueBoost8.png","blueBoost9.png","blueBoost10.png","blueBoost11.png","blueBoost12.png",
        "blueBoost13.png","blueBoost14.png","blueBoost15.png","blueBoost16.png","blueBoost17.png","blueBoost18.png","blueBoost19.png",
        "blueBoost20.png","blueBoost21.png","blueBoost22.png","blueBoost23.png","blueBoost24.png"});
        frameSpeed=4;
        Vy=1;
        count=0;
    }



}
