package lilylicious.brief.events;

import lilylicious.brief.BriefCore;
import lilylicious.brief.recipes.CraftContainer;
import lilylicious.brief.recipes.IngredientCache;
import lilylicious.brief.recipes.RecipeContainer;
import lilylicious.brief.utils.GuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("unused")
public class PlayerEvents {

    private KeyBinding READ_ITEM;

    /* Bindings collection to register at runtime */
    public PlayerEvents() {
        READ_ITEM = new KeyBinding("Read Item", Keyboard.KEY_NUMLOCK, BriefCore.MODNAME);
        ClientRegistry.registerKeyBinding(READ_ITEM);
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent e) {
        if (READ_ITEM.isPressed()) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;

            ItemStack stack = new ItemStack(Blocks.OAK_FENCE);

            CraftContainer cContainer = new CraftContainer(stack);

            //GuiHandler.rootItem = new RecipeContainer(stack);
            //player.openGui(BriefCore.instance, 0, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
        }
    }

    public void getItemUnderMouse(){

    }
}
