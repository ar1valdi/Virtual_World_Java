package MainPackage.Exceptions;

public class PosOutOfMap extends Exception {
    public PosOutOfMap() {
        super();
    }

    public PosOutOfMap(String msg) {
        super(msg);
    }

    public PosOutOfMap(String msg, Throwable cause) {
        super(msg, cause);
    }
}
