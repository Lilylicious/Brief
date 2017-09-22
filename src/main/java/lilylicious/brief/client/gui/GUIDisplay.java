package lilylicious.brief.client.gui;

import lilylicious.brief.BriefCore;
import lilylicious.brief.recipes.CraftContainer;
import lilylicious.brief.recipes.RecipeContainer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GUIDisplay extends GuiScreen {

    private static final ResourceLocation texture = new ResourceLocation(BriefCore.MODID.toLowerCase(), "textures/gui/recipes_gui.png");

    GuiButton closeButton;

    private int xSize;
    private int ySize;
    private int guiLeft;
    private int guiTop;

    private CraftContainer craftContainer;

    public GUIDisplay(CraftContainer container){
        craftContainer = container;
    }

    @Override
    public void initGui() {

        this.xSize = 175;
        this.ySize = 184;

        this.guiLeft = (width - this.xSize) / 2;
        this.guiTop = (height - this.ySize) / 2;

        this.buttonList.add(closeButton = new GuiButton(0, this.width / 2 - 25, guiTop + ySize - 30, 50, 20, "Close"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawContainers();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == closeButton) {
                this.mc.setIngameFocus();
        }
    }

   private void drawContainers(){
        craftContainer.renderContainer(guiLeft + 10, guiTop + 10);
   }
}
