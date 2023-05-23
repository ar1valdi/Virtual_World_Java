package MainPackage.Exceptions;

public class SpawnOnTakenSpace extends Exception {
    public SpawnOnTakenSpace() {
        super();
    }

    public SpawnOnTakenSpace(String msg) {
        super(msg);
    }

    public SpawnOnTakenSpace(String msg, Throwable cause) {
        super(msg, cause);
    }
}
