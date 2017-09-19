package lilylicious.brief.proxies;

import lilylicious.brief.events.PlayerEvents;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy implements IProxy {
    @Override
    public EntityPlayer getClientPlayer() {
        return FMLClientHandler.instance().getClientPlayerEntity();
    }

    @Override
    public void registerClientOnlyEvents() {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
    }

    @Override
    public void registerModels() {
    }

    private void registerItem(Item i) {
        registerItem(i, 0);
    }

    private void registerItem(Item i, int meta) {
        String name = ForgeRegistries.ITEMS.getKey(i).toString();
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(name, "inventory"));
    }

    private void registerBlock(Block b) {
        String name = ForgeRegistries.BLOCKS.getKey(b).toString();
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(name, "inventory"));
    }
}

