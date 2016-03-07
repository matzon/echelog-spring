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

package dk.matzon.echelog.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for Network. A NetworkDTOs channels may be weak
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NetworkDTO implements Serializable {

    private String name;

    private List<ChannelDTO> channels;

    public NetworkDTO(String _name) {
        this.name = _name;
    }

    public NetworkDTO(String _name, List<ChannelDTO> _channels) {
        this.name = _name;
        this.channels = _channels;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkDTO that = (NetworkDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(channels, that.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public List<ChannelDTO> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return "NetworkDTO{" +
                "name='" + name + '\'' +
                ", channels=" + channels +
                '}';
    }
}
