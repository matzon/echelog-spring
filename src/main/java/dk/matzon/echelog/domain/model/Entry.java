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

import java.util.Date;
import java.util.Objects;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Entry implements Entity<Entry> {

    public enum Type {
        CONTROL,
        CHAT
    }

    private long id;
    private Date date;
    private String text;
    private Type type;

    public Entry() {
    }

    public Entry(long _id, Date _date, String _text, Type _type) {
        id = _id;
        date = _date;
        text = _text;
        type = _type;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date _date) {
        date = _date;
    }

    public String getText() {
        return text;
    }

    public void setText(String _text) {
        text = _text;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type _type) {
        type = _type;
    }

    @Override
    public boolean sameIdentityAs(Entry _other) {
        return id == _other.id;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Entry entry = (Entry) _o;
        return id == entry.id &&
                Objects.equals(date, entry.date) &&
                Objects.equals(text, entry.text) &&
                type == entry.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, text, type);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
