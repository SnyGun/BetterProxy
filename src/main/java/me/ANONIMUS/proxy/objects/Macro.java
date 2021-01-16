package me.ANONIMUS.proxy.objects;

import lombok.Data;
import me.ANONIMUS.proxy.protocol.objects.Player;
import me.ANONIMUS.proxy.protocol.packet.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Data
public class Macro {
    private final int id;
    public static List<Packet> packets = new ArrayList<>();

    public static void addPacket(Packet packet){
        packets.add(packet);
    }

    public static void resetMacro(){
        packets.clear();
    }

    public static void playPacket(Player player){
        Thread thread0 = new Thread(() -> {
            player.getBots().forEach(bot -> {
                Thread thread1 = new Thread(() -> {
                    try {
                        for(int i = 0; i< packets.size(); i++){
                            TimeUnit.MILLISECONDS.sleep(45);
                            bot.getSession().sendPacket(packets.get(i));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                thread1.start();
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        thread0.start();
    }

}
