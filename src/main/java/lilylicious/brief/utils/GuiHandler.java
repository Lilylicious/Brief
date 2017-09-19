package lilylicious.brief.utils;

import com.google.common.collect.ImmutableSet;
import lilylicious.brief.recipes.RecipeContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import lilylicious.brief.client.gui.GUIDisplay;

import java.util.Set;

public class GuiHandler implements IGuiHandler {

    //IDs for non-tile GUIs
    private static final Set<Integer> ITEM_IDS = ImmutableSet.of();

    public static RecipeContainer rootItem;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case 0:
                    return new GUIDisplay(rootItem);

        }

        return null;
    }

}
