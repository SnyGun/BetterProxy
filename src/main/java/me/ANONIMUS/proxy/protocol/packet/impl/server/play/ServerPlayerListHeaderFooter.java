package me.ANONIMUS.proxy.protocol.packet.impl.server.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ANONIMUS.proxy.protocol.packet.Packet;
import me.ANONIMUS.proxy.protocol.packet.PacketBuffer;
import me.ANONIMUS.proxy.protocol.packet.Protocol;
import me.ANONIMUS.proxy.utils.ChatUtil;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.chat.ComponentSerializer;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServerPlayerListHeaderFooter extends Packet {
    private BaseComponent[] header, footer;

    public ServerPlayerListHeaderFooter(String header, String footer) {
        this(new ComponentBuilder(ChatUtil.fixColor(header)).create(), new ComponentBuilder(ChatUtil.fixColor(footer)).create());
    }

    @Override
    public void write(PacketBuffer out, int protocol) throws Exception {
        out.writeString(ComponentSerializer.toString(this.header));
        out.writeString(ComponentSerializer.toString(this.footer));
    }

    @Override
    public void read(PacketBuffer in, int protocol) throws Exception {
        this.header = ComponentSerializer.parse(in.readString());
        this.footer = ComponentSerializer.parse(in.readString());
    }

    @Override
    public List<Protocol> getProtocolList() {
        return Arrays.asList(new Protocol(0x47, 47), new Protocol(0x47, 110), new Protocol(0x4A, 340));
    }
}