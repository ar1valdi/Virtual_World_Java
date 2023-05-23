package MainPackage.Exceptions;

public class InvalidType extends Exception{
    public InvalidType(){
        super();
    }

    public InvalidType(String msg){
        super(msg);
    }

    public InvalidType(String msg, Throwable cause){
        super(msg, cause);
    }
}
