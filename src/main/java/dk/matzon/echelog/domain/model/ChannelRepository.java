package dk.matzon.echelog.domain.model;

import java.util.List;

/**
 * Repository for all channels
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public interface ChannelRepository {
    List<Channel> findAll(boolean includeArchived);

    List<Channel> findByNetwork(Network _network);

    Channel findByNetworkAndName(Network _network, String _name);
}
