package me.ANONIMUS.proxy.command.impl.more;

import me.ANONIMUS.proxy.command.Command;
import me.ANONIMUS.proxy.enums.CommandType;
import me.ANONIMUS.proxy.enums.ConnectedType;
import me.ANONIMUS.proxy.protocol.objects.Player;
import me.ANONIMUS.proxy.utils.ChatUtil;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommandPortScanner extends Command {
    public CommandPortScanner() { super("portscanner", null, null, "[ip] [start port] [end port]", CommandType.MORE, ConnectedType.NONE); }

    @Override
    public void onCommand(Player sender, String cmd, String[] args) throws Exception {
        String host = args[1];
        if (host.contains(":")) {
            host = host.split(":", 2)[0];
        }
        ChatUtil.sendChatMessage("&7Port scan started &2" + host, sender, true);
        ChatUtil.sendChatMessage("&7Started port &c" + args[2], sender, true);
        List<Integer> ports = new ArrayList<>();
        String finalHost = host;
        new Thread(() -> {
            for (int i = Integer.parseInt(args[2]); i < Integer.parseInt(args[3]); ++i) {
                try {
                    final Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(finalHost, i), 500);
                    ports.add(i);
                    ChatUtil.sendChatMessage("&7A working port has been found &a" + i, sender, true);
                } catch (Exception ignored){ }
            }
            ChatUtil.sendChatMessage("&7Scanning is complete &8(&e" + ports.size() + "&8)", sender, true);
            ChatUtil.sendChatMessage("&7Open ports: &6" + ports, sender, true);
            ports.clear();
        }).start();
    }
}