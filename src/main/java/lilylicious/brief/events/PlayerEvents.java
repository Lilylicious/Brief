package lilylicious.brief.events;

import lilylicious.brief.BriefCore;
import lilylicious.brief.jei.BriefPlugin;
import lilylicious.brief.recipes.CraftContainer;
import lilylicious.brief.utils.GuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

@SuppressWarnings("unused")
public class PlayerEvents {

    private KeyBinding READ_ITEM;

    /* Bindings collection to register at runtime */
    public PlayerEvents() {
        READ_ITEM = new KeyBinding("Read Item", Keyboard.KEY_A, BriefCore.MODNAME);
        ClientRegistry.registerKeyBinding(READ_ITEM);


    }

    @SubscribeEvent
    public void onKeyInputEvent(GuiScreenEvent.KeyboardInputEvent event) {
        if (Keyboard.getEventKey() == READ_ITEM.getKeyCode() && !Keyboard.getEventKeyState()) {
            EntityPlayerSP player = Minecraft.getMinecraft().player;

            /*
            ItemStack stack = new ItemStack(Blocks.ANVIL);

            ItemStack[] testCases = {new ItemStack(Blocks.ANVIL),
                    new ItemStack(Blocks.OAK_FENCE),
                    new ItemStack(Items.WRITABLE_BOOK),
                    new ItemStack(Blocks.BED),
                    new ItemStack(Blocks.CRAFTING_TABLE),
                    new ItemStack(Blocks.WOODEN_SLAB)
            };

            for(ItemStack stack : testCases)
                new CraftContainer(stack);
            */

            ItemStack underMouse = BriefPlugin.getItemUnderMouse();
            if (!underMouse.isEmpty()) {
                GuiHandler.rootItem = new CraftContainer(underMouse);
                player.openGui(BriefCore.instance, 0, player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ);
            }

        }
    }

    public void getItemUnderMouse() {

    }
}
