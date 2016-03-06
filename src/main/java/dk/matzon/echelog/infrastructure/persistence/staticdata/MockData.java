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

package dk.matzon.echelog.infrastructure.persistence.staticdata;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.Network;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@Component
public class MockData {

    /* package protected per network mapped store */
    private Map<Network, List<Channel>> store;

    public MockData() {
        store = new HashMap<>();

        // freenode
        Network freenode = new Network("freenode", new ArrayList<Channel>());
        ArrayList<Channel> freenodeChannels = new ArrayList<>();
        freenodeChannels.add(new Channel(freenode, "#angularjs", "Official AngularJS IRC channel", "http://angularjs.org/", false));
        freenodeChannels.add(new Channel(freenode, "#jasig-uportal", "Official uPortal IRC channel", "http://www.jasig.org/uportal", false));
        freenode.setChannels(freenodeChannels);
        store.put(freenode, freenodeChannels);

        // codehaus
        ArrayList<Channel> codehausChannels = new ArrayList<>();
        Network codehaus = new Network("codehaus", new ArrayList<Channel>());
        codehausChannels.add(new Channel(codehaus, "#apache-ode", "Official Apache ODE (Orchestration Director Engine) channel", "http://ode.apache.org/", true));
        codehaus.setChannels(codehausChannels);
        store.put(codehaus, codehausChannels);
    }

    public Map<Network, List<Channel>> getStore() {
        return store;
    }
}