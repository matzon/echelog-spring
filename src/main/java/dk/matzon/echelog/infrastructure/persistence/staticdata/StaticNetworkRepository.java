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
import dk.matzon.echelog.domain.model.NetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@Repository
public class StaticNetworkRepository implements NetworkRepository {

    private Map<Network, List<Channel>> store;

    @Autowired
    public StaticNetworkRepository(MockData _data) {
        this.store = _data.getStore();
    }

    @Override
    public List<Network> findAll() {
        return new ArrayList<>(store.keySet());
    }

    @Override
    public Network findByName(String _name) {
        for (Network network : store.keySet()) {
            if (network.getName().equals(_name)) {
                return network;
            }
        }
        return null;
    }
}
