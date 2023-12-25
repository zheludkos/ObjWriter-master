package org.example.ObjWriter;

import org.example.Model.Polygon;

import java.io.FileWriter;
import java.io.IOException;

public class ObjWrtError {

    public static FileWriter fileCorrectness(String objFile) {
        try {
            return new FileWriter(objFile);
        } catch (IOException e) {
            System.err.println("This file not found, therefore a file was created in a directory called 'objFile'");
            try {
                return new FileWriter(objFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void checkingForNull(Object obj) {
        if (obj == null) {
            try {
                throw new ObjWriterException("The element is null.");
            } catch (ObjWriterException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void structurePolygon(Polygon polygon) {
        if (polygon.getVertexIndices().size() < 3) {
            try {
                throw new ObjWriterException("A polygon cannot have fewer than two vertices");
            } catch (ObjWriterException e) {
                throw new RuntimeException(e);
            }
        } else if (!polygon.getTextureVertexIndices().isEmpty() && polygon.getTextureVertexIndices().size() != polygon.getVertexIndices().size()) {
            try {
                throw new ObjWriterException("The number of vertices and UV is not equivalent");
            } catch (ObjWriterException e) {
                throw new RuntimeException(e);
            }
        } else if (!polygon.getNormalIndices().isEmpty() && polygon.getNormalIndices().size() != polygon.getVertexIndices().size()) {
            try {
                throw new ObjWriterException("The number of vertices and normals is not equivalent");
            } catch (ObjWriterException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
