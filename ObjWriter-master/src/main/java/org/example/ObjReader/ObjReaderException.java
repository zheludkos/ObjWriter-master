package org.example.ObjReader;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String errorMessage, int lineInd) {
        super("Error parsing OBJ file on line: " + lineInd + ". " + errorMessage);
    }
}
