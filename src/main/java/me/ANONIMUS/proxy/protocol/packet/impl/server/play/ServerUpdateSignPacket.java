package me.ANONIMUS.proxy.protocol.packet.impl.server.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ANONIMUS.proxy.protocol.data.Position;
import me.ANONIMUS.proxy.protocol.packet.Packet;
import me.ANONIMUS.proxy.protocol.packet.PacketBuffer;
import me.ANONIMUS.proxy.protocol.packet.Protocol;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServerUpdateSignPacket extends Packet {
    private Position position;
    private String line1, line2, line3, line4;

    @Override
    public void write(PacketBuffer out, int protocol) throws Exception {
        out.writePosition(position);
        out.writeString(line1);
        out.writeString(line2);
        out.writeString(line3);
        out.writeString(line4);
    }

    @Override
    public void read(PacketBuffer in, int protocol) throws Exception {
        this.position = in.readPosition();
        this.line1 = in.readString();
        this.line2 = in.readString();
        this.line3 = in.readString();
        this.line4 = in.readString();
    }

    @Override
    public List<Protocol> getProtocolList() {
        return Collections.singletonList(new Protocol(0x33, 47));
    }
}