package me.ANONIMUS.proxy.command.impl.normal;

import me.ANONIMUS.proxy.BetterProxy;
import me.ANONIMUS.proxy.command.Command;
import me.ANONIMUS.proxy.enums.CommandType;
import me.ANONIMUS.proxy.enums.ConnectedType;
import me.ANONIMUS.proxy.exploits.Exploit;
import me.ANONIMUS.proxy.protocol.objects.Player;
import me.ANONIMUS.proxy.utils.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public class CommandCrash extends Command {
    public CommandCrash() {
        super("crash", "exploit", ";D", "[method, list] [amount]", CommandType.NORMAL, ConnectedType.NONE);
    }

    @Override
    public void onCommand(Player sender, String cmd, String[] args) throws Exception {
        if (args.length == 2) {
            if (args[1].equals("list")) {
                List<String> exploits = new ArrayList<>();
                BetterProxy.getInstance().getExploitManager().getExploits().forEach(exploit -> exploits.add(exploit.getName()));
                ChatUtil.sendChatMessage("&8>> &6" + exploits.toString().replace("[", "").replace("]", ""), sender, false);
            }
            return;
        }
        Exploit exploit = BetterProxy.getInstance().getExploitManager().findExploit(args[1]);
        if (exploit == null) {
            ChatUtil.sendChatMessage("&4Unknown method!", sender, true);
            return;
        }
        if(sender.getBots().size() == 0 && !sender.isConnected()) {
            ChatUtil.sendChatMessage("&4You must be connected to the server!", sender, true);
            return;
        }
        exploit.execute(sender, Integer.parseInt(args[2]));
    }
}