package amerifrance.fluxedmagictools;

import amerifrance.fluxedmagictools.proxy.CommonProxy;
import amerifrance.fluxedmagictools.registry.BlockRegistry;
import amerifrance.fluxedmagictools.registry.ItemRegistry;
import amerifrance.fluxedmagictools.registry.RecipeRegistry;
import amerifrance.fluxedmagictools.util.handler.EventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import lombok.Getter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.common.config.ConfigItems;

import java.io.File;
import java.util.Locale;

@Mod(name = FluxedMagicTools.NAME, modid = FluxedMagicTools.MODID, version = FluxedMagicTools.VERSION)
public class FluxedMagicTools {

    @Getter protected static final String MODID = "FluxedMagicTools";
    @Getter protected static final String NAME = "FluxedMagicTools";
    @Getter protected static final String DOMAIN = MODID.toLowerCase(Locale.ENGLISH) + ":";
    @Getter protected static final String VERSION = "@VERSION@";
    @Getter protected static final String CLIENTPROXY = "amerifrance.fluxedmagictools.proxy.ClientProxy";
    @Getter protected static final String COMMONPROXY = "amerifrance.fluxedmagictools.proxy.CommonProxy";
    @Getter protected static final String DEPEND = "required-after:Thaumcraft;";

    public static CreativeTabs tabFluxedMagicTools = new CreativeTabs(MODID + ".creativeTab") {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(ConfigItems.itemHoverHarness, 1, 0);
        }

        @Override
        public Item getTabIconItem() {
            return ConfigItems.itemHoverHarness;
        }
    };

    @Mod.Instance
    public static FluxedMagicTools instance;

    @SidedProxy(clientSide = FluxedMagicTools.CLIENTPROXY, serverSide = FluxedMagicTools.COMMONPROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(new File(event.getModConfigurationDirectory(), "FluxedMagicTools.cfg"));

        EventHandler eventHandler = new EventHandler();
        FMLCommonHandler.instance().bus().register(eventHandler);
        MinecraftForge.EVENT_BUS.register(eventHandler);

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ItemRegistry.register();
        BlockRegistry.register();
        RecipeRegistry.register();

        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
