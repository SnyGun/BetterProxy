package me.ANONIMUS.proxy.protocol.packet.impl.client.play;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ANONIMUS.proxy.protocol.data.ItemStack;
import me.ANONIMUS.proxy.protocol.packet.Packet;
import me.ANONIMUS.proxy.protocol.packet.PacketBuffer;
import me.ANONIMUS.proxy.protocol.packet.Protocol;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreativeInventoryAction extends Packet {
    private int slot;
    private ItemStack itemStack;

    @Override
    public void write(PacketBuffer out, int protocol) throws Exception {
        out.writeShort(slot);
        out.writeItemStackToBuffer(itemStack);
    }

    @Override
    public void read(PacketBuffer in, int protocol) throws Exception {
        this.slot = in.readShort();
        this.itemStack = in.readItemStackFromBuffer();
    }

    @Override
    public List<Protocol> getProtocolList() {
        return Arrays.asList(new Protocol(0x10, 47), new Protocol(0x10, 110), new Protocol(0x10, 340));
    }
}