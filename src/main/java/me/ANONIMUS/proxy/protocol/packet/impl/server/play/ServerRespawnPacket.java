package me.ANONIMUS.proxy.protocol.packet.impl.server.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ANONIMUS.proxy.protocol.data.Difficulty;
import me.ANONIMUS.proxy.protocol.data.Dimension;
import me.ANONIMUS.proxy.protocol.data.Gamemode;
import me.ANONIMUS.proxy.protocol.packet.Packet;
import me.ANONIMUS.proxy.protocol.packet.PacketBuffer;
import me.ANONIMUS.proxy.protocol.packet.Protocol;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServerRespawnPacket extends Packet {

    {
        this.getProtocolList().add(new Protocol(0x07, 47));
        this.getProtocolList().add(new Protocol(0x33, 110));
        this.getProtocolList().add(new Protocol(0x35, 340));
    }

    private Dimension dimension;
    private Difficulty difficulty;
    private Gamemode gamemode;
    private String level_type;


    @Override
    public void write(PacketBuffer out, int protocol) throws Exception {
        out.writeInt(this.dimension.getId());
        out.writeByte(this.difficulty.getId());
        out.writeByte(this.gamemode.getId());
        out.writeString(this.level_type);
    }

    @Override
    public void read(PacketBuffer in, int protocol) throws Exception {
        this.dimension = Dimension.getById(in.readInt());
        this.difficulty = Difficulty.getById(in.readUnsignedByte());
        this.gamemode = Gamemode.getById(in.readUnsignedByte());
        this.level_type = in.readString(16);
    }
}