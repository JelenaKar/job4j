package ru.job4j.xmlconverter;

import javax.xml.bind.annotation.*;
import java.util.List;

public class XmlUsage {

    @XmlRootElement
    public static class Entries {
        private List<Entry> values;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        @XmlElement(name = "entry")
        public List<Entry> getValues() {
            return values;
        }

        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Entry {
        private int field;

        public Entry() {
        }

        public Entry(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "Entry{field=" + field + '}';
        }
    }
}
