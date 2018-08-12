package edu.bzu.jhipsterapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Tile entity.
 */
public class TileDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 4)
    private String code;

    @NotNull
    private String size;

    @NotNull
    private String placement;

    @NotNull
    private String polishType;

    @NotNull
    private Double costPrice;

    @NotNull
    private Integer minimumSellingPrice;

    @NotNull
    private Integer inventoryQunatity;

    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getPolishType() {
        return polishType;
    }

    public void setPolishType(String polishType) {
        this.polishType = polishType;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getMinimumSellingPrice() {
        return minimumSellingPrice;
    }

    public void setMinimumSellingPrice(Integer minimumSellingPrice) {
        this.minimumSellingPrice = minimumSellingPrice;
    }

    public Integer getInventoryQunatity() {
        return inventoryQunatity;
    }

    public void setInventoryQunatity(Integer inventoryQunatity) {
        this.inventoryQunatity = inventoryQunatity;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TileDTO tileDTO = (TileDTO) o;
        if (tileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", size='" + getSize() + "'" +
            ", placement='" + getPlacement() + "'" +
            ", polishType='" + getPolishType() + "'" +
            ", costPrice=" + getCostPrice() +
            ", minimumSellingPrice=" + getMinimumSellingPrice() +
            ", inventoryQunatity=" + getInventoryQunatity() +
            ", company=" + getCompanyId() +
            "}";
    }
}
