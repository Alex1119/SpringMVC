package Model;


public class Response<T>{
    public int Code;
    public String Message;
    public T Data;

    public Response(){}

    public Response(Class<T> clz){}

    public Response(int code, String message, T data) {
        Code = code;
        Message = message;
        Data = data;
    }
}
