package me.ANONIMUS.proxy.command.impl;

import me.ANONIMUS.proxy.BetterProxy;
import me.ANONIMUS.proxy.command.Command;
import me.ANONIMUS.proxy.enums.ConnectedType;
import me.ANONIMUS.proxy.protocol.objects.Player;
import me.ANONIMUS.proxy.protocol.packet.impl.server.play.ServerChatPacket;
import me.ANONIMUS.proxy.utils.ChatUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandHelp extends Command {
    public CommandHelp() {
        super("help", null, "helpful commands", "[page]", null, ConnectedType.NONE);
    }

    @Override
    public void onCommand(Player sender, String cmd, String[] args) throws Exception {
        List<Command> list = new ArrayList<>();
        BetterProxy.getInstance().getCommandManager().getCommands().stream().filter(command -> command.getCommandType() != null).forEach(list::add);
        list.sort(Comparator.comparingInt(s -> s.getPrefix().length()));

        int i = 1;
        if(args.length == 2) {
            i = Integer.parseInt(args[1]);
        }

        int k = i - 1;
        int l = Math.min((k + 1) * 10, list.size());
        int j = (list.size() - 1) / 10;

        if(!(i > 0)) {
            ChatUtil.sendChatMessage ("&cThe given number (" + i + ") is too small, must be at least 1", sender, false);
            return;
        }

        if(i > (j + 1)) {
            ChatUtil.sendChatMessage ("&cThe given number (" + (k + 1) + ") is too high, cannot exceed" + (j + 1), sender, false);
            return;
        }

        ChatUtil.clearChat(1, sender);
        ChatUtil.sendChatMessage (" &8 --- Showing help page: &6" + (k + 1) + " &8of &6" + (j + 1) + " &8---", sender, false);
        for (int i1 = k * 10; i1 < l; ++i1) {
            final Command command = list.get(i1);
            ChatUtil.sendHoverMessage(sender, "&8>> &f" + sender.getPrefixCMD() + command.getPrefix() + " &7" + command.getUsage(), "&6" + command.getDesc());
        }

        Component msg = Component.text(ChatUtil.fixColor((i != 1) ? "&c[PREVIOUS]": ""))
                .hoverEvent(HoverEvent.showText(Component.text(ChatUtil.fixColor("&eClick to go to the previous page!"))))
                .clickEvent(ClickEvent.runCommand(sender.getPrefixCMD() + "help " + (i - 1)))
            .append(Component.text(" "))
            .append(Component.text(ChatUtil.fixColor((k + 1) != (j + 1) ? "&a[NEXT]" : ""))
                .hoverEvent(HoverEvent.showText(Component.text(ChatUtil.fixColor("&eClick to go to the next page!"))))
                .clickEvent(ClickEvent.runCommand(sender.getPrefixCMD() + "help " + (i + 1)))
            );

        sender.getSession().sendPacket(new ServerChatPacket(msg));
    }
}