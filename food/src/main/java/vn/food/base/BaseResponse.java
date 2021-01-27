package vn.food.base;


import vn.food.error.StoreError;

/**
 * Package name : com.web.store.reponse
 * Created by   : tannv
 * Created at   : 11/15/20
 */
public class BaseResponse<T> {
    /**
     * The type Field name.
     */
    public static class FieldName {
        /**
         * Instantiates a new Field name.
         */
        protected FieldName() {
        }

        /**
         * The constant RESULT_CODE.
         */
        public static final String RESULT_CODE = "resultCode";
        /**
         * The constant RESULT_MESSAGE.
         */
        public static final String RESULT_MESSAGE = "resultMessage";
        /**
         * The constant BODY.
         */
        public static final String BODY = "body";

    }

    private int resultCode = StoreError.SUCCESS.code;

    private String resultMessage = StoreError.SUCCESS.desc;

    private T body;

    public BaseResponse() {
    }


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
