/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Brian Matzon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dk.matzon.echelog.interfaces.rest;

import dk.matzon.echelog.application.Echelog;
import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.Network;
import dk.matzon.echelog.interfaces.ChannelFacade;
import dk.matzon.echelog.interfaces.NetworkFacade;
import dk.matzon.echelog.interfaces.assembler.ChannelAssembler;
import dk.matzon.echelog.interfaces.assembler.NetworkAssembler;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import dk.matzon.echelog.interfaces.dto.NetworkDTO;
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
public class NetworkController implements NetworkFacade {

    private Echelog echelog;

    public NetworkController() {
    }

    @Autowired
    public NetworkController(Echelog _echelog) {
        this.echelog = _echelog;
    }

    @Override
    @RequestMapping(path = "/networks", method = RequestMethod.GET)
    public Collection<NetworkDTO> listAllNetwork(@RequestParam(value = "includeChannels", defaultValue = "false") boolean _includeChannels) {
        List<NetworkDTO> result = new ArrayList<>();
        List<Network> networks = echelog.getNetworks();
        if (!networks.isEmpty()) {
            result = new ArrayList<>(NetworkAssembler.toDTO(networks, _includeChannels));
        }
        return result;
    }

    @Override
    @RequestMapping(path = "/networks/{name}", method = RequestMethod.GET)
    public NetworkDTO findByName(@PathVariable("name") String _network) {
        NetworkDTO result = null;
        Network network = echelog.getNetwork(_network);
        if (network != null) {
            result = NetworkAssembler.toDTO(network, true);
        }
        return result;
    }
}