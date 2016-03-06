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

package dk.matzon.echelog.domain.model;

import se.citerus.dddsample.domain.shared.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * POJO representing a network
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Network implements Entity<Network> {

    private String name;

    List<Channel> channels;

    public Network() {
    }

    public Network(String name, List<Channel> _channels) {
        this.name = name;
        this.channels = _channels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Channel> getChannels() {
        return getChannels(false);
    }

    public List<Channel> getChannels(boolean _onlyActive) {
        ArrayList<Channel> filtered = new ArrayList<>(channels);
        if (_onlyActive) {
            Iterator<Channel> iterator = filtered.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().isArchived()) {
                    iterator.remove();
                }
            }
        }
        return filtered;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public boolean sameIdentityAs(Network _channel) {
        return name.equals(_channel.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return Objects.equals(name, network.name) &&
                Objects.equals(channels, network.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Network{" +
                "name='" + name + '\'' +
                ", channels=" + channels +
                '}';
    }
}
