package edu.patronovskiy.studentorder.domain;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public enum StudentOrderStatus {
    START, CHECKED;

    public static StudentOrderStatus fromValue(int value) {
        for (StudentOrderStatus sos : StudentOrderStatus.values()) {
            if(sos.ordinal() == value) {
                return sos;
            }
        }
        throw new RuntimeException("Uknown value of StudentOrderStatus: " + value);
    }
}
