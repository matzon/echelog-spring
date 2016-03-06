package dk.matzon.echelog.interfaces.rest;

import dk.matzon.echelog.application.Echelog;
import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.Network;
import dk.matzon.echelog.interfaces.ChannelFacade;
import dk.matzon.echelog.interfaces.assembler.ChannelAssembler;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Rest access point for Echelog application
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ChannelController implements ChannelFacade {

    private Echelog echelog;

    public ChannelController() {
    }

    @Autowired
    public ChannelController(Echelog _echelog) {
        this.echelog = _echelog;
    }

    @Override
    @RequestMapping(path = "/channels", method = RequestMethod.GET)
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

    @Override
    @RequestMapping(path = "/channels/{name}", method = RequestMethod.GET)
    public Collection<ChannelDTO> listChannelsByName(@PathVariable("name") String _name) {
        List<ChannelDTO> result = new ArrayList<>();
        List<Channel> allChannels = echelog.getAllChannels();
        if(!allChannels.isEmpty()) {
            for (Channel channel : allChannels) {
                if(_name.equals(channel.getName())) {
                    result.add(ChannelAssembler.toDTO(channel, null));
                }
            }
        }
        return result;
    }

    @Override
    @RequestMapping(path = "/{network}/channels", method = RequestMethod.GET)
    public Collection<ChannelDTO> listChannelsByNetwork(@PathVariable("network") String _network, boolean _onlyActive) {
        List<ChannelDTO> result = new ArrayList<>();
        List<Channel> channels = new ArrayList<>();

        Network network = echelog.getNetwork(_network);
        if(network != null) {
            channels = network.getChannels(_onlyActive);
        }

        if (!channels.isEmpty()) {
            result = new ArrayList<>(ChannelAssembler.toDTO(channels));
        }
        return result;
    }
}