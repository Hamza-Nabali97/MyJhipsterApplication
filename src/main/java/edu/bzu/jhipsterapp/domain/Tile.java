package edu.bzu.jhipsterapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Tile.
 */
@Entity
@Table(name = "tile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 4)
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "jhi_size", nullable = false)
    private String size;

    @NotNull
    @Column(name = "placement", nullable = false)
    private String placement;

    @NotNull
    @Column(name = "polish_type", nullable = false)
    private String polishType;

    @NotNull
    @Column(name = "cost_price", nullable = false)
    private Double costPrice;

    @NotNull
    @Column(name = "minimum_selling_price", nullable = false)
    private Integer minimumSellingPrice;

    @NotNull
    @Column(name = "inventory_qunatity", nullable = false)
    private Integer inventoryQunatity;

    @ManyToOne
    @JsonIgnoreProperties("tiles")
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Tile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Tile code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public Tile size(String size) {
        this.size = size;
        return this;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPlacement() {
        return placement;
    }

    public Tile placement(String placement) {
        this.placement = placement;
        return this;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getPolishType() {
        return polishType;
    }

    public Tile polishType(String polishType) {
        this.polishType = polishType;
        return this;
    }

    public void setPolishType(String polishType) {
        this.polishType = polishType;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public Tile costPrice(Double costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getMinimumSellingPrice() {
        return minimumSellingPrice;
    }

    public Tile minimumSellingPrice(Integer minimumSellingPrice) {
        this.minimumSellingPrice = minimumSellingPrice;
        return this;
    }

    public void setMinimumSellingPrice(Integer minimumSellingPrice) {
        this.minimumSellingPrice = minimumSellingPrice;
    }

    public Integer getInventoryQunatity() {
        return inventoryQunatity;
    }

    public Tile inventoryQunatity(Integer inventoryQunatity) {
        this.inventoryQunatity = inventoryQunatity;
        return this;
    }

    public void setInventoryQunatity(Integer inventoryQunatity) {
        this.inventoryQunatity = inventoryQunatity;
    }

    public Company getCompany() {
        return company;
    }

    public Tile company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tile tile = (Tile) o;
        if (tile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", size='" + getSize() + "'" +
            ", placement='" + getPlacement() + "'" +
            ", polishType='" + getPolishType() + "'" +
            ", costPrice=" + getCostPrice() +
            ", minimumSellingPrice=" + getMinimumSellingPrice() +
            ", inventoryQunatity=" + getInventoryQunatity() +
            "}";
    }
}
