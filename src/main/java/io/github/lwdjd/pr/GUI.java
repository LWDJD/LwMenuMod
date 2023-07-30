package io.github.lwdjd.pr;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

@Mod(modid = "lwmenu", name = "Lw Menu MOD", version = "1.0.0.1")
public class GUI {

    // 创建一个继承自GuiScreen的类，代表一个GUI界面
    public static class MenuGui extends GuiScreen {

        // 用来存储一个GUI按钮
        private GuiButton myButton;

        // 重写initGui方法，用来初始化GUI组件
        @Override
        public void initGui() {
// 调用父类的方法
            super.initGui();
// 创建一个GUI按钮，参数分别是按钮的id，x坐标，y坐标，宽度，高度，文本
            myButton = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 20, 200, 40, "Click Me!");
// 将按钮添加到GUI界面中
            this.buttonList.add(myButton);
        }

        // 重写drawScreen方法，用来绘制GUI元素
        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
// 调用父类的方法
            super.drawScreen(mouseX, mouseY, partialTicks);
// 绘制一个灰色的背景
            this.drawDefaultBackground();
// 绘制一个居中的标题文本，参数分别是文本，x坐标，y坐标，颜色
            this.drawCenteredString(this.fontRenderer, "My GUI", this.width / 2, 20, 0xFFFFFF);
        }

        // 重写keyTyped方法，用来处理键盘输入
        @Override
        protected void keyTyped(char typedChar, int keyCode) throws IOException {
// 调用父类的方法
            super.keyTyped(typedChar, keyCode);
// 如果按下了Esc键，就关闭GUI界面
            if (keyCode == 1) {
                this.mc.displayGuiScreen(null);
            }
        }

        // 重写mouseClicked方法，用来处理鼠标点击
        @Override
        protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
// 调用父类的方法
            super.mouseClicked(mouseX, mouseY, mouseButton);
// 如果点击了左键，就判断是否点击了按钮
            if (mouseButton == 0) {
                for (GuiButton button : this.buttonList) {
                    if (button.mousePressed(this.mc, mouseX, mouseY)) {
// 如果点击了按钮，就调用actionPerformed方法
                        this.actionPerformed(button);
                    }
                }
            }
        }

        // 重写actionPerformed方法，用来处理按钮点击事件
        @Override
        protected void actionPerformed(GuiButton button) {
// 判断点击的按钮是否是我们定义的按钮
            if (button == myButton) {
// 在这里可以执行你想要的操作，例如打印一条消息
                System.out.println("You clicked my button!");
            }
        }
    }

    // 注册一个事件订阅者，用来处理GuiOpenEvent事件
    @Mod.EventBusSubscriber
    public static class GuiHandler {
        // 在@SubscribeEvent注解的方法中处理GuiOpenEvent事件
        @SubscribeEvent
        public static void onGuiOpen(GuiOpenEvent event) {
// 判断打开的GUI是否是游戏菜单（按下Esc键时打开的）
            if (event.getGui() instanceof net.minecraft.client.gui.GuiIngameMenu) {
// 如果是游戏菜单，就取消这个事件，并打开我们自定义的GUI界面
                event.setCanceled(true);
                event.setGui(new MenuGui());
            }
        }
    }
}