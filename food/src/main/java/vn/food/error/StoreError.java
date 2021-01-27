package vn.food.error;

/**
 * Package name : com.web.store.common
 * Created by   : tannv
 * Created at   : 11/15/20
 */
public enum StoreError {
    // BASE ERROR
    SUCCESS(0, "Success"),
    INVALID_MSG(-1, "Fields have error"),
    SYSTEM_ERROR(-2, "System have error");

    public final int code;
    public final String desc;

    StoreError(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
