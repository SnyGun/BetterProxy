package me.ANONIMUS.proxy.command.impl.bots;

        import me.ANONIMUS.proxy.command.Command;
        import me.ANONIMUS.proxy.enums.CommandType;
        import me.ANONIMUS.proxy.enums.ConnectedType;
        import me.ANONIMUS.proxy.protocol.objects.Bot;
        import me.ANONIMUS.proxy.protocol.objects.Player;
        import me.ANONIMUS.proxy.protocol.packet.impl.client.play.ClientChatPacket;
        import me.ANONIMUS.proxy.utils.ChatUtil;

        import java.util.Random;
        import java.util.concurrent.TimeUnit;

public class CommandAutoregister extends Command {
    public CommandAutoregister() {
        super("autoregister", null, "autoregister bot", "[repeat]", CommandType.BOTS, ConnectedType.CONNECTED);
    }

    protected String getRandomString(int lenght) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < lenght) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @Override
    public void onCommand(Player sender, String cmd, String[] args) throws Exception {
        if(sender.getBots().size() == 0){
            ChatUtil.sendChatMessage("&cYou don't have any connected bots", sender, true);
            return;
        }else {
                int repeat = Integer.parseInt(args[1]);
                if(repeat == 1){
                    Thread thread0 = new Thread(() -> {
                    for (Bot bot : sender.getBots()) {
                        String password = getRandomString(6);
                        bot.getSession().sendPacket(new ClientChatPacket("/register " + password));
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    });
                    thread0.start();
                    ChatUtil.sendChatMessage("&7Successfully register !", sender, true);
                } else if(repeat == 2){
                    Thread thread1 = new Thread(() -> {
                    for (Bot bot : sender.getBots()) {
                        String password = getRandomString(6);
                        bot.getSession().sendPacket(new ClientChatPacket("/register " + password + " " + password));
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    });
                    thread1.start();
                    ChatUtil.sendChatMessage("&7Successfully register !", sender, true);
                }
        }
    }
}