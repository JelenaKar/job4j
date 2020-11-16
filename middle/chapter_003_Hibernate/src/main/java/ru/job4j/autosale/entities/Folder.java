package ru.job4j.autosale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folder_gen")
    @SequenceGenerator(name = "folder_gen", sequenceName = "folder_id_seq", allocationSize = 1)
    private int id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Photo> photos;

    public static Folder of(int id) {
        Folder folder = new Folder();
        folder.id = id;
        return folder;
    }

    public static Folder of(String name, Ad ad) {
        Folder folder = new Folder();
        folder.name = name;
        folder.ad = ad;
        return folder;
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

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Folder bodyStyle = (Folder) o;
        return id == bodyStyle.id;
    }

    @Override
    public String toString() {
        return "Folder{"
                + "id=" + id
                + ", name=" + name
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
