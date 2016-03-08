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

import java.util.Date;
import java.util.Objects;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class EntryDTO {
    private long id;
    private Date date;
    private String text;
    private String type;

    public EntryDTO() {
    }

    public EntryDTO(long _id, Date _date, String _text, String _type) {
        id = _id;
        date = _date;
        text = _text;
        type = _type;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        EntryDTO entryDTO = (EntryDTO) _o;
        return id == entryDTO.id &&
                Objects.equals(date, entryDTO.date) &&
                Objects.equals(text, entryDTO.text) &&
                Objects.equals(type, entryDTO.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, text, type);
    }

    @Override
    public String toString() {
        return "EntryDTO{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
