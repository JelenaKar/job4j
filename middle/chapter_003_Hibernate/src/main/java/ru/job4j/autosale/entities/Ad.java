package ru.job4j.autosale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_gen")
    @SequenceGenerator(name = "ad_gen", sequenceName = "ad_id_seq", allocationSize = 1)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_id")
    private Auto auto;
    private int price;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    private long created;
    @Column(name = "issold")
    private boolean isSold;

    @JsonIgnore
    @OneToOne(mappedBy = "ad", cascade = CascadeType.ALL)
    private Folder folder;

    public static Ad of(int id) {
        Ad ad = new Ad();
        ad.id = id;
        return ad;
    }

    public static Ad of(Auto auto, int price, Seller seller, long created, boolean isSold) {
        Ad ad = new Ad();
        ad.auto = auto;
        ad.price = price;
        ad.seller = seller;
        ad.created = created;
        ad.isSold = isSold;
        return ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public boolean getisSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder album) {
        this.folder = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ad{"
                + "id=" + id
                + ", auto=" + auto
                + ", price=" + price
                + ", user=" + seller
                + ", created=" + created
                + ", isSold=" + isSold
                + ", folder=" + folder
                + '}';
    }
}
