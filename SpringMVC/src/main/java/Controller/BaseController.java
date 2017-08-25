package Controller;

import org.jetbrains.annotations.NotNull;

public class BaseController {

    @NotNull
    public static <T> Response<T> ResponseData(int Code, String Message, T Data){
        return new Response<T>(Code, Message, Data);
    }

    @NotNull
    public static <T> Response<T> ResponseData(String Message, T Data){
        return new Response<T>(1, Message, Data);
    }

    @NotNull
    public static <T> Response<T> ResponseData(T Data){
        return new Response<T>(1,"操作成功！", Data);
    }

}

