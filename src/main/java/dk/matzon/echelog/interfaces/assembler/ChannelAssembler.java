package dk.matzon.echelog.interfaces.assembler;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class ChannelAssembler {
    /**
     * Assemble a Channel to ChannelDTO
     */
    public static ChannelDTO toDTO(Channel _channel) {
        return new ChannelDTO(_channel.getId(), _channel.getName(), _channel.getDescription(), _channel.getUrl(), _channel.isArchived());
    }

    /**
     * Assemble a collection of Channel to ChannelDTO
     */
    public static Collection<ChannelDTO> toDTO(Collection<Channel> channels) {
        ArrayList<ChannelDTO> result = new ArrayList<>();
        for (Channel channel : channels) {
            result.add(toDTO(channel));
        }
        return result;
    }

    public static Channel fromDTO(ChannelDTO _channelDTO) {
        return new Channel(_channelDTO.getId(), _channelDTO.getName(), _channelDTO.getDescription(), _channelDTO.getUrl(), _channelDTO.isArchived());
    }
}
