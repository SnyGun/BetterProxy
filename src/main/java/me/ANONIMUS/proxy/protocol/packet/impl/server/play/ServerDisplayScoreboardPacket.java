package me.ANONIMUS.proxy.protocol.packet.impl.server.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ANONIMUS.proxy.protocol.packet.Packet;
import me.ANONIMUS.proxy.protocol.packet.PacketBuffer;
import me.ANONIMUS.proxy.protocol.packet.Protocol;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ServerDisplayScoreboardPacket extends Packet {
    private int position;
    private String scoreName;

    @Override
    public void write(PacketBuffer out, int protocol) throws Exception {
        out.writeByte(this.position);
        out.writeString(this.scoreName);
    }

    @Override
    public void read(PacketBuffer in, int protocol) throws Exception {
        this.position = in.readByte();
        this.scoreName = in.readString(32767);
    }

    @Override
    public List<Protocol> getProtocolList() {
        return Arrays.asList(new Protocol(0x3D, 47), new Protocol(0x38, 110), new Protocol(0x3B, 340));
    }
}