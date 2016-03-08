package dk.matzon.echelog.application;

import dk.matzon.echelog.domain.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Echelog {

    private NetworkRepository networkRepository;
    private ChannelRepository channelRepository;
    private EntryRepository entryRepository;

    public Echelog(NetworkRepository _networkRepository, ChannelRepository _channelRepository, EntryRepository _entryRepository) {
        this.channelRepository = _channelRepository;
        this.networkRepository = _networkRepository;
        this.entryRepository = _entryRepository;
    }

    @Transactional
    public List<Channel> getActiveChannels() {
        return channelRepository.findAll(false);
    }

    @Transactional
    public List<Channel> getAllChannels() {
        return channelRepository.findAll(true);
    }

    @Transactional
    public Network getNetwork(String _network) {
        return networkRepository.findByName(_network);
    }

    @Transactional
    public List<Network> getNetworks() {
        return networkRepository.findAll();
    }

    @Transactional
    public List<Entry> getLogsForDate(Network _network, Channel _channel, Date _start, Date _end) {
        return entryRepository.findAll(_network, _channel, _start, _end);
    }

    @Transactional
    public List<Channel> getChannelsForNetwork(Network _network) {
        return channelRepository.findByNetwork(_network);
    }

    @Transactional
    public Network addNetwork(Network _network) {
        return networkRepository.save(_network);
    }

    @Transactional
    public Channel addChannel(Channel _channel) {
        return channelRepository.save(_channel);
    }
}
