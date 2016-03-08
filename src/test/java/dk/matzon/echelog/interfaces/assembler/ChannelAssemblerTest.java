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
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class ChannelAssemblerTest {

    @Test
    public void testSingleChannelSerialized() throws Exception {
        Channel channel = new Channel(1, "channelname", "description", "url", false);
        ChannelDTO channelDTO = ChannelAssembler.toDTO(channel);

        assertEquals(channel.getName(), channelDTO.getName());
        assertEquals(channel.getDescription(), channelDTO.getDescription());
        assertEquals(channel.getUrl(), channelDTO.getUrl());
        assertEquals(channel.isArchived(), channelDTO.isArchived());
    }

    @Test
    public void testCollectionChannelSerialized() throws Exception {
        List<Channel> channelList = new ArrayList<>();
        channelList.add(new Channel(1, "channelname-1", "description", "url", false));
        channelList.add(new Channel(2, "channelname-2", "description", "url", false));
        List<ChannelDTO> channelDTOs = new ArrayList<>(ChannelAssembler.toDTO(channelList));

        assertEquals(channelList.size(), channelDTOs.size());
        assertEquals(channelList.get(0).getName(), channelDTOs.get(0).getName());
        assertEquals(channelList.get(1).getName(), channelDTOs.get(1).getName());
    }
}