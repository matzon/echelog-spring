package dk.matzon.echelog.interfaces;

import dk.matzon.echelog.interfaces.dto.ChannelDTO;

import java.util.Collection;

/**
 * Facade for shielding domain from interfaces
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public interface ChannelFacade {
    Collection<ChannelDTO> listAllChannels(boolean _onlyActive);
    Collection<ChannelDTO> listChannelsByName(String _name);
    Collection<ChannelDTO> listChannelsByNetwork(String _network, boolean _onlyActive);
    ChannelDTO save(ChannelDTO _channelDTO);
}
