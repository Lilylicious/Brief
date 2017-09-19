package lilylicious.brief.proxies;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy {
    EntityPlayer getClientPlayer();

    void registerClientOnlyEvents();
    void registerModels();
}
