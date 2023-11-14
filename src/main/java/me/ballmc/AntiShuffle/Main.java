package me.ballmc.AntiShuffle;

import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.*;
import me.ballmc.AntiShuffle.command.AntiShuffleToggle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import java.util.regex.Pattern;

public class Main implements ModInitializer {
    public static boolean enabled = true;
    private static final Pattern shufflePattern = Pattern.compile("\u00a7k[OX]*");

    public static void setEnabled(boolean value) {
        enabled = value;
        EnumChatFormatting statusColor = enabled ? EnumChatFormatting.GREEN : EnumChatFormatting.RED;
        String message = String.format("%sAntiShuffle%s has been %s%s%s.",
        EnumChatFormatting.BLUE, EnumChatFormatting.RESET,
        statusColor, (enabled ? "enabled" : "disabled"), EnumChatFormatting.RESET);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
    }

    public static String getUnformattedTextForChat(String shuffleText) {
        return shufflePattern.matcher(shuffleText).replaceAll("");
    }

    @Override
    public void preInit() {
        System.out.println("Initializing AntiShuffle!");
        CommandBus.register(new AntiShuffleToggle());
    }
}