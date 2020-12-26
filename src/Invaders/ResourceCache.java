package Invaders;

import java.util.HashMap;
import java.net.URL;
/**
 *
 * @author Link
 */
public abstract class ResourceCache {
    protected HashMap resources;

    public ResourceCache(){
        resources = new HashMap();
    }



    protected Object loadResource(String dir){
        URL url = null;
        url = getClass().getClassLoader().getResource(dir);
        return loadResource(url);
    }

    protected Object getResource(String name){
        Object res = resources.get(name);
        if(res==null){
            res = loadResource("res/"+name);
            resources.put(name,res);
        }
        return res;
    }

    protected abstract Object loadResource(URL url);

}
