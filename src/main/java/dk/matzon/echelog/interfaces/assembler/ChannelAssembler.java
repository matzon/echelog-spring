package dk.matzon.echelog.interfaces.assembler;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import dk.matzon.echelog.interfaces.dto.NetworkDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class ChannelAssembler {
    /**
     * Assemble a Channel to ChannelDTO
     *
     * @param _network parent NetworkDTO to use (or null)
     */
    public static ChannelDTO toDTO(Channel _channel, NetworkDTO _network) {
        return new ChannelDTO(_network, _channel.getName(), _channel.getDescription(), _channel.getUrl(), _channel.isArchived());
    }

    /**
     * Assemble a collection of Channel to ChannelDTO
     */
    public static Collection<ChannelDTO> toDTO(Collection<Channel> channels) {
        ArrayList<ChannelDTO> result = new ArrayList<>();
        for (Channel channel : channels) {
            result.add(toDTO(channel, new NetworkDTO(channel.getNetwork().getName())));
        }
        return result;
    }
}
