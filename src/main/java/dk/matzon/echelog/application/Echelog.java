package dk.matzon.echelog.application;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Echelog {

    private ChannelRepository channelRepository;

    @Autowired
    public Echelog(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getActiveChannels() {
        return channelRepository.findAll(false);
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll(true);
    }
}
