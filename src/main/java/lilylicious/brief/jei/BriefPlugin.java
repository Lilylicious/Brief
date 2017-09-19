package lilylicious.brief.jei;

import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;

public class BriefPlugin implements IModPlugin {
    public static IJeiRuntime runtime;

    public static void getItemUnderMouse(){
        Object o = runtime.getIngredientListOverlay().getIngredientUnderMouse();


    }
}
