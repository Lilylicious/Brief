package lilylicious.brief;

import lilylicious.brief.config.BriefConfig;
import lilylicious.brief.proxies.IProxy;
import lilylicious.brief.utils.GuiHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod(modid = BriefCore.MODID, name = BriefCore.MODNAME, version = BriefCore.VERSION, dependencies = "required-after:jei@[4.7.8.95,)", clientSideOnly = true)
public class BriefCore
{
    public static final String MODID = "brief";
    public static final String MODNAME = "Brief";
    public static final String VERSION = "0.1";

    @Mod.Instance(MODID)
    public static BriefCore instance;

    @SidedProxy(clientSide = "lilylicious.brief.proxies.ClientProxy", serverSide = "lilylicious.brief.proxies.ServerProxy")
    private static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BriefConfig.init(new File(event.getModConfigurationDirectory(), "Brief.cfg"));

        NetworkRegistry.INSTANCE.registerGuiHandler(BriefCore.instance, new GuiHandler());

        proxy.registerClientOnlyEvents();
        proxy.registerModels();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
    }
}
