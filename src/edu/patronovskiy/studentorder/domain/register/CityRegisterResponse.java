package edu.patronovskiy.studentorder.domain.register;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class CityRegisterResponse {
    private boolean existing;   //существует ли такой человек в реестре
    private Boolean temporal;   //временная ли регистрация

    public boolean isExisting() {
        return existing;
    }

    public void setExisting(final boolean existing) {
        this.existing = existing;
    }

    public Boolean getTemporal() {
        return temporal;
    }

    public void setTemporal(final Boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterCheckerResponse{" +
            "existing=" + existing +
            ", temporal=" + temporal +
            '}';
    }
}
