package Invaders;

/**
 *
 * @author Link
 */
public class TimeWave extends Live{

    public TimeWave(Space space){
        super(space);
        setSpriteNames(new String[]{"TimeWave_1.png","TimeWave_2.png","TimeWave_3.png","TimeWave_4.png","TimeWave_5.png",
                                    "TimeWave_6.png","TimeWave_7.png","TimeWave_8.png","TimeWave_9.png","TimeWave_10.png",
                                    "TimeWave_11.png","TimeWave_12.png","TimeWave_13.png","TimeWave_14.png","TimeWave_15.png",
                                    "TimeWave_16.png","TimeWave_17.png","TimeWave_18.png","TimeWave_19.png","TimeWave_20.png",
                                    "TimeWave_21.png","TimeWave_22.png","TimeWave_23.png","TimeWave_24.png","TimeWave_25.png",
                                    "TimeWave_26.png","TimeWave_27.png","TimeWave_28.png","TimeWave_29.png","TimeWave_30.png",
                                    "TimeWave_31.png","TimeWave_32.png","TimeWave_33.png","TimeWave_34.png","TimeWave_35.png",
                                    "TimeWave_36.png","TimeWave_37.png","TimeWave_38.png","TimeWave_39.png","TimeWave_40.png",
                                    "TimeWave_41.png","TimeWave_42.png","TimeWave_43.png","TimeWave_44.png","TimeWave_45.png",
                                    "TimeWave_46.png","TimeWave_47.png","TimeWave_48.png","TimeWave_49.png","TimeWave_50.png",
                                    "TimeWave_51.png","TimeWave_52.png","TimeWave_53.png","TimeWave_54.png","TimeWave_55.png",
                                    "TimeWave_56.png","TimeWave_57.png","TimeWave_58.png","TimeWave_59.png","TimeWave_60.png",
                                    "TimeWave_61.png","TimeWave_62.png","TimeWave_63.png","TimeWave_64.png","TimeWave_65.png",
                                    "TimeWave_66.png","TimeWave_67.png","TimeWave_68.png","TimeWave_69.png","TimeWave_70.png",
                                    "TimeWave_71.png","TimeWave_72.png",});
        frameSpeed=2;
        
    }



@Override
    public void act(){

          localTime++;
            if(localTime%frameSpeed==0){
             currentFrame=(currentFrame+1)%spriteNames.length;
             localTime=0;
             if (currentFrame==0) this.reaper();
     }
    }


}
