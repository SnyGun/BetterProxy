package me.ANONIMUS.proxy.protocol.packet.impl.server.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.ANONIMUS.proxy.protocol.packet.Packet;
import me.ANONIMUS.proxy.protocol.packet.PacketBuffer;
import me.ANONIMUS.proxy.protocol.packet.Protocol;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ServerStatusPongPacket extends Packet {

    {
        this.getProtocolList().add(new Protocol(0x01, 47));
        this.getProtocolList().add(new Protocol(0x01, 110));
        this.getProtocolList().add(new Protocol(0x01, 340));
    }

    private long time;

    @Override
    public void write(PacketBuffer out, int protocol) throws Exception {
        out.writeLong(this.time);
    }

    @Override
    public void read(PacketBuffer in, int protocol) throws Exception {
        this.time = in.readLong();
    }
}