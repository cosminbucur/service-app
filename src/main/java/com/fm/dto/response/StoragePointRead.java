package com.fm.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StoragePointRead {

    private Long id;
    private String code;
    private String licensePlate;
    private int numberOfRimCaps;
    private boolean cleared;
    private LocalDate clearedDate;

    private List<TyreRead> mountedTyres = new ArrayList<>();
    private List<TyreRead> storedTyres = new ArrayList<>();

    public StoragePointRead() {
    }

    public void removeTyres(List<TyreRead> list) {
        storedTyres.removeAll(list);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getNumberOfRimCaps() {
        return numberOfRimCaps;
    }

    public void setNumberOfRimCaps(int numberOfRimCaps) {
        this.numberOfRimCaps = numberOfRimCaps;
    }

    public List<TyreRead> getMountedTyres() {
        return mountedTyres;
    }

    public void setMountedTyres(List<TyreRead> mountedTyres) {
        this.mountedTyres = mountedTyres;
    }

    public List<TyreRead> getStoredTyres() {
        return storedTyres;
    }

    public void setStoredTyres(List<TyreRead> storedTyres) {
        this.storedTyres = storedTyres;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }

    public LocalDate getClearedDate() {
        return clearedDate;
    }

    public void setClearedDate(LocalDate clearedDate) {
        this.clearedDate = clearedDate;
    }

    @Override
    public String toString() {
        return "StoragePointRead{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", licensePlate='" + licensePlate + '\'' +
            ", numberOfRimCaps=" + numberOfRimCaps +
            ", cleared=" + cleared +
            ", clearedDate=" + clearedDate +
            ", mountedTyres=" + mountedTyres +
            ", storedTyres=" + storedTyres +
            '}';
    }
}
