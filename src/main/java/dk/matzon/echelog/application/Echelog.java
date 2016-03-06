package dk.matzon.echelog.application;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.ChannelRepository;
import dk.matzon.echelog.domain.model.Network;
import dk.matzon.echelog.domain.model.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Echelog {

    private NetworkRepository networkRepository;
    private ChannelRepository channelRepository;

    @Autowired
    public Echelog(ChannelRepository _channelRepository, NetworkRepository _networkRepository) {
        this.channelRepository = _channelRepository;
        this.networkRepository = _networkRepository;
    }

    public List<Channel> getActiveChannels() {
        return channelRepository.findAll(false);
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll(true);
    }

    public Network getNetwork(String _network) {
        return networkRepository.findByName(_network);
    }

    public List<Network> getNetworks() {
        return networkRepository.findAll();
    }
}
