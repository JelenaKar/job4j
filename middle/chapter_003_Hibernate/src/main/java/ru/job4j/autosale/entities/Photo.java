package ru.job4j.autosale.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity(name = "photo")
public class Photo {
    public static final String SYSTEM_PATH = System.getProperty("user.home") + File.separator + "bin" + File.separator + "images";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_gen")
    @SequenceGenerator(name = "photo_gen", sequenceName = "photo_id_seq", allocationSize = 1)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Folder folder;

    public static Photo of(int id) {
        Photo photo = new Photo();
        photo.id = id;
        return photo;
    }

    public static Photo of(String name, Folder folder) {
        Photo photo = new Photo();
        photo.name = name;
        photo.folder = folder;
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Photo photo = (Photo) o;
        return id == photo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getFolder() + "/" + name;
    }
}