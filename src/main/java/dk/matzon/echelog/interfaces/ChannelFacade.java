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
}
