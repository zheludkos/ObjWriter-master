package org.example.ObjWriter;

import org.example.Math.Vector2f;
import org.example.Math.Vector3f;
import org.example.Model.Model;
import org.example.Model.Polygon;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ObjWriter {

    public void recordModel(String objFile, Model model) throws IOException {
        if (model == null) {
            throw new IllegalArgumentException("Model is null");
        }

        FileWriter fileWriter = ObjWrtError.fileCorrectness(objFile);
        recordVertices(fileWriter, model.getVertices(), "v");
        recordTextureVertices(fileWriter, model.getTextureVertices(), "vt");
        recordNormals(fileWriter, model.getNormals(), "vn");
        recordPolygons(fileWriter, model.getPolygons());
        fileWriter.flush();
    }

    public void recordTextureVertices(FileWriter fileWriter, ArrayList<Vector2f> textureVertices, String prefix) throws IOException {
        for (Vector2f textureVertex : textureVertices) {
            fileWriter.write(String.format(Locale.US,"%s %f %f\n", prefix, textureVertex.getX(), textureVertex.getY()));
        }
    }


    public void recordNormals(FileWriter fileWriter, ArrayList<Vector3f> normals, String prefix) throws IOException {
        for (Vector3f normal : normals) {
            fileWriter.write(String.format(Locale.US,"%s %f %f %f\n", prefix, normal.getX(), normal.getY(), normal.getZ()));
        }
    }


    public void recordVertices(FileWriter fileWriter, ArrayList<Vector3f> vertices, String prefix) throws IOException {
        for (Vector3f vertex : vertices) {
            if (vertex != null) {
                fileWriter.write(String.format(Locale.US,"%s %f %f %f\n", prefix, vertex.getX(), vertex.getY(), vertex.getZ()));
            }
        }
    }

    public void recordPolygons(FileWriter fileWriter, ArrayList<Polygon> polygons) throws IOException {
        for (Polygon polygon : polygons) {
            ObjWrtError.checkingForNull(polygon);
            fileWriter.write(polygonBuilder(polygon) + "\n");
        }
    }


    private String polygonBuilder(Polygon polygon) {
        StringBuilder returnString = new StringBuilder("F" + " ");
        ObjWrtError.structurePolygon(polygon);
        int vertexIndices = polygon.getVertexIndices().size();

        for (int i = 0; i < vertexIndices; i++) {
            returnString.append(polygon.getVertexIndices().get(i));
            if (!polygon.getTextureVertexIndices().isEmpty()) {
                returnString.append("/").append(polygon.getTextureVertexIndices().get(i));
            }
            if (!polygon.getNormalIndices().isEmpty()) {
                returnString.append("/").append(polygon.getNormalIndices().get(i));
            }
            if (i < vertexIndices - 1) {
                returnString.append(" ");
            }
        }
        return returnString.toString();
    }
    public static FileWriter fileCorrectness(String fileName) {
        try {
            return new FileWriter(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
