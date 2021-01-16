package me.ANONIMUS.proxy.commands.bots;

import me.ANONIMUS.proxy.handler.impl.ServerPlayHandler;
import me.ANONIMUS.proxy.objects.Command;
import me.ANONIMUS.proxy.objects.Macro;
import me.ANONIMUS.proxy.protocol.objects.Player;
import me.ANONIMUS.proxy.enums.CommandType;
import me.ANONIMUS.proxy.enums.ConnectedType;
import me.ANONIMUS.proxy.utils.ChatUtil;

public class CommandMacro extends Command {
    public CommandMacro() {
        super("macro", null, "", "[record/play]", CommandType.BOTS, ConnectedType.CONNECTED);
    }
    long begin;
    long end;

    public void setBegin(long begin){
        this.begin = begin;
    }

    public void setEnd(long end){
        this.end = end;
    }

    public long getEnd(){
        return end;
    }

    public long getBegin(){
        return begin;
    }

    @Override
    public void onCommand(Player sender, String cmd, String[] args) throws Exception {
        if(args[1].equalsIgnoreCase("record")){
            if(sender.getMacroregister() == false){
                Macro.resetMacro();
                sender.setMacroregister(true);
                long begin = System.currentTimeMillis();
                setBegin(begin);
                ChatUtil.sendChatMessage("You successfully record your packets", sender, true);
            } else {
                sender.setMacroregister(false);
                ServerPlayHandler.resetpacketint();
                long end = System.currentTimeMillis();
                setEnd(end);
                final long begintest = getBegin();
                final long endtest = getEnd();
                final String elapsed = String.valueOf(endtest - begintest);
                ChatUtil.sendChatMessage("You successfully stop record your packets", sender, true);
                ChatUtil.sendChatMessage("recorded : " + elapsed + " ms", sender, true);
            }
        } else if (args[1].equalsIgnoreCase("play")){
            sender.getBots().forEach(bot -> Macro.playPacket(sender));
            ChatUtil.sendChatMessage("Your bots will play your recorded packets", sender, true);
        }
    }
}