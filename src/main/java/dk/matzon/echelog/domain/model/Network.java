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

import java.util.Objects;

/**
 * POJO representing a network
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Network implements Entity<Network> {

    private long id;
    private String name;

    public Network() {
    }

    public Network(long _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    @Override
    public boolean sameIdentityAs(Network _network) {
        return id == _network.id;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Network network = (Network) _o;
        return id == network.id &&
                Objects.equals(name, network.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Network{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
