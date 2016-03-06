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

package dk.matzon.echelog.interfaces.assembler;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.Network;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import dk.matzon.echelog.interfaces.dto.NetworkDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class NetworkAssembler {
    public static NetworkDTO toDTO(Network _network) {
        NetworkDTO result = new NetworkDTO(_network.getName(), new ArrayList<ChannelDTO>());
        for (Channel channel : _network.getChannels(false)) {
            ChannelDTO channelDTO = ChannelAssembler.toDTO(channel, result);

            /* add to network */
            result.getChannels().add(channelDTO);
        }

        return result;
    }

    public static Collection<NetworkDTO> toDTO(Collection<Network> _networks) {
        ArrayList<NetworkDTO> result = new ArrayList<>();

        for (Network network : _networks) {
            result.add(toDTO(network));
        }

        return result;
    }
}
