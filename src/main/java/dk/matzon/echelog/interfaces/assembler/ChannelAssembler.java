package dk.matzon.echelog.interfaces.assembler;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class ChannelAssembler {
    public static ChannelDTO toDTO(Channel _channel) {
        return new ChannelDTO(_channel.getNetwork(), _channel.getName(), _channel.getDescription(), _channel.getUrl(), _channel.isArchived());
    }

    public static Collection<ChannelDTO> toDTO(Collection<Channel> channels) {
        ArrayList<ChannelDTO> result = new ArrayList<>();

        for (Channel channel : channels) {
            result.add(toDTO(channel));
        }

        return result;
    }
}