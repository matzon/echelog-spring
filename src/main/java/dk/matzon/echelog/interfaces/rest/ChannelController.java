package dk.matzon.echelog.interfaces.rest;

import dk.matzon.echelog.application.Echelog;
import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.interfaces.ChannelFacade;
import dk.matzon.echelog.interfaces.assembler.ChannelAssembler;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Rest access point for Echelog application
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
@RestController
public class ChannelController implements ChannelFacade {

    private Echelog echelog;

    public ChannelController() {
    }

    @Autowired
    public ChannelController(Echelog _echelog) {
        this.echelog = _echelog;
    }

    @Override
    @RequestMapping("/channels")
    public Collection<ChannelDTO> listAllChannels(@RequestParam(value = "onlyActive", defaultValue = "false") boolean _onlyActive) {
        List<ChannelDTO> result = new ArrayList<>();
        List<Channel> channels;

        if (_onlyActive) {
            channels = echelog.getActiveChannels();
        } else {
            channels = echelog.getAllChannels();
        }

        if (!channels.isEmpty()) {
            result = new ArrayList<>(ChannelAssembler.toDTO(channels));
        }
        return result;
    }
}