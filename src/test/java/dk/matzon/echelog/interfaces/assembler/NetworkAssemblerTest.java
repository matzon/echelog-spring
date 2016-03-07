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
import dk.matzon.echelog.interfaces.dto.NetworkDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class NetworkAssemblerTest {
    @Test
    public void testSingleNetworkNoChannelsSerialized() throws Exception {
        Network network = new Network("networkname", null);
        NetworkDTO networkDTO = NetworkAssembler.toDTO(network, false);

        assertEquals(network.getName(), networkDTO.getName());
    }

    @Test
    public void testSingleNetworkNoChannelsSerializedFull() throws Exception {
        Network network = new Network("networkname", null);
        NetworkDTO networkDTO = NetworkAssembler.toDTO(network, true);

        assertEquals(network.getName(), networkDTO.getName());
    }

    @Test
    public void testSingleNetworkWithChannelsSerialized() throws Exception {
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(new Channel(null, "channelname", "description", "url", false));
        Network network = new Network("networkname", channels);
        channels.get(0).setNetwork(network);

        NetworkDTO networkDTO = NetworkAssembler.toDTO(network, false);

        assertEquals(network.getName(), networkDTO.getName());
        assertEquals(0, networkDTO.getChannels().size());
    }

    @Test
    public void testSingleNetworkWithChannelsSerializedFull() throws Exception {
        ArrayList<Channel> channels = new ArrayList<>();
        channels.add(new Channel(null, "channelname", "description", "url", false));
        Network network = new Network("networkname", channels);
        channels.get(0).setNetwork(network);

        NetworkDTO networkDTO = NetworkAssembler.toDTO(network, true);

        assertEquals(network.getName(), networkDTO.getName());
        assertEquals(channels.size(), networkDTO.getChannels().size());
    }

    @Test
    public void testCollectionNetworkNoChannelsSerialized() throws Exception {
        List<Network> networkList = new ArrayList<>();
        networkList.add(new Network("networkname-1", null));
        networkList.add(new Network("networkname-2", null));

        List<NetworkDTO> networkDTOs = new ArrayList<>(NetworkAssembler.toDTO(networkList, false));

        assertEquals(networkList.size(), networkDTOs.size());
        assertEquals(networkList.get(0).getName(), networkDTOs.get(0).getName());
    }
}