package com.gtmdmock.admin.model.vo;

import java.io.Serializable;

import static com.gtmdmock.admin.model.enums.BaseResponseEnums.*;

public class BaseResponseVO implements Serializable {

        private int code;
        private String message;
        private Object data;

        public static BaseResponseVO success(Object data){
            return new BaseResponseVO(SUCCESS.getCode(),SUCCESS.getMessage(),data);
        }

        public static BaseResponseVO fail(Object data){
            return new BaseResponseVO(FAIL.getCode(),FAIL.getMessage(),data);
        }

        public static BaseResponseVO error(Object data){
            return new BaseResponseVO(ERROR.getCode(),ERROR.getMessage(),data);
        }

        public static BaseResponseVO warn(Class<?> aClass, String eMessage, String message, String s){
            return new BaseResponseVO(ERROR.getCode(),ERROR.getMessage(),aClass);
        }

        BaseResponseVO(int code, String message, Object data) {

            this.code = code;
            this.message = message;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "BaseResponseVO{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
