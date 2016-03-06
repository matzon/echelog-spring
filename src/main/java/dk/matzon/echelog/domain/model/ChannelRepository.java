package dk.matzon.echelog.domain.model;

import java.util.List;

/**
 * Repository for all channels
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public interface ChannelRepository {
    List<Channel> findAll(boolean includeArchived);

    List<Channel> findByNetwork(String _network);

    Channel findByNetworkAndName(String _network, String _name);
}
