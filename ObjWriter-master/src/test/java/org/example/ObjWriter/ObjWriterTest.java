package org.example.ObjWriter;

import org.example.Math.Vector2f;
import org.example.Math.Vector3f;
import org.example.Model.Model;
import org.example.Model.Polygon;
import org.example.ObjReader.ObjReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ObjWriterTest {
    private final String NAME_FILE = "Test.obj";

    @Test
    public void testCreateFile() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
    }

    @Test
    public void testRecordNull() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);

        ArrayList<Vector3f> vertices = new ArrayList<>();
        vertices.add(new Vector3f(0.22f, 0.33f, 0.11f));
        vertices.add(new Vector3f(0.22f, 0.35f, 0.12f));
        vertices.add(null);
        vertices.add(new Vector3f(0.1f, 0.23f, 0.14f));
        vertices.add(new Vector3f(0.50f, 0.11f, 0.85f));

        try {
            objWriter.recordVertices(fileWriter, vertices, "v");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRecordVertices() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        String strokeInfo = "v 0.42 0.63 0.99\nv 0.28 0.16 0.44\nv 0.2 0.19 0.18\nv 0.01 0.05 0.76\n";
        String line = "";

        ArrayList<Vector3f> vertices = new ArrayList<>();
        vertices.add(new Vector3f(0.42f, 0.63f, 0.99f));
        vertices.add(new Vector3f(0.28f, 0.16f, 0.44f));
        vertices.add(new Vector3f(0.2f, 0.19f, 0.18f));
        vertices.add(new Vector3f(0.01f, 0.05f, 0.76f));

        try {
            objWriter.recordVertices(fileWriter, vertices, "v");
            BufferedReader reader = new BufferedReader(new FileReader(NAME_FILE));
            while ((line = reader.readLine()) != null) {
                line += reader.readLine() + "\n";
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(strokeInfo, line);
    }

    @Test
    public void testRecordTextureVertices() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        String strokeInfo = "vt 0.33 0.11\nvt 0.35 0.12\nvt 0.23 0.14\nvt 0.11 0.85\n";
        String line = "";

        ArrayList<Vector2f> textureVertices = new ArrayList<>();
        textureVertices.add(new Vector2f(0.33f, 0.11f));
        textureVertices.add(new Vector2f(0.35f, 0.12f));
        textureVertices.add(new Vector2f(0.23f, 0.14f));
        textureVertices.add(new Vector2f(0.11f, 0.85f));

        try {
            objWriter.recordTextureVertices(fileWriter, textureVertices, "vt");
            BufferedReader reader = new BufferedReader(new FileReader(NAME_FILE));
            while ((line = reader.readLine()) != null) {
                line += reader.readLine() + "\n";
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(strokeInfo, line);
    }

    @Test
    public void testRecordNormals() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        ArrayList<Vector3f> normals = new ArrayList<>();
        String strokeInfo = "vn 0.1 0.2 0.3\nvn 0.4 0.5 0.6\nvn 0.7 0.8 0.9\nvn 0.11 0.12 0.13\n";
        String line = "";

        normals.add(new Vector3f(0.1f, 0.2f, 0.3f));
        normals.add(new Vector3f(0.4f, 0.5f, 0.6f));
        normals.add(new Vector3f(0.7f, 0.8f, 0.9f));
        normals.add(new Vector3f(0.11f, 0.12f, 0.13f));

        try {
            objWriter.recordNormals(fileWriter, normals, "vn");
            BufferedReader reader = new BufferedReader(new FileReader(NAME_FILE));
            while ((line = reader.readLine()) != null) {
                line += reader.readLine() + "\n";
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(strokeInfo, line);
    }

    @Test
    public void testRecordPolygons() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        ArrayList<Polygon> polygons = new ArrayList<>();
        String strokeInfo = "f 1/5/9 2/6/10 3/7/11 4/8/12";
        String line = "";

        ArrayList<Integer> vertexIndices = new ArrayList<>();
        vertexIndices.add(1);
        vertexIndices.add(2);
        vertexIndices.add(3);
        vertexIndices.add(4);

        ArrayList<Integer> textureVertexIndices = new ArrayList<>();
        textureVertexIndices.add(5);
        textureVertexIndices.add(6);
        textureVertexIndices.add(7);
        textureVertexIndices.add(8);

        ArrayList<Integer> normalIndices = new ArrayList<>();
        normalIndices.add(9);
        normalIndices.add(10);
        normalIndices.add(11);
        normalIndices.add(12);

        polygons.add(new Polygon());
        polygons.get(0).setVertexIndices(vertexIndices);
        polygons.get(0).setTextureVertexIndices(textureVertexIndices);
        polygons.get(0).setNormalIndices(normalIndices);

        try {
            objWriter.recordPolygons(fileWriter, polygons);
            BufferedReader reader = new BufferedReader(new FileReader(NAME_FILE));
            BufferedReader readerNull = new BufferedReader(new FileReader(NAME_FILE));

            while (readerNull.readLine() != null) {
                line += reader.readLine();
            }
            reader.close();
            readerNull.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(strokeInfo, line);
    }

    @Test
    public void testRecordPolygonsWithoutUV() {
        ArrayList<Polygon> polygons = new ArrayList<>();
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        String strokeInfo = "f 1//9 2//10 3//11 4//12";
        String line = "";

        ArrayList<Integer> vertexIndices = new ArrayList<>();
        vertexIndices.add(1);
        vertexIndices.add(2);
        vertexIndices.add(3);
        vertexIndices.add(4);

        ArrayList<Integer> normalIndices = new ArrayList<>();
        normalIndices.add(9);
        normalIndices.add(10);
        normalIndices.add(11);
        normalIndices.add(12);

        polygons.add(new Polygon());
        polygons.get(0).setVertexIndices(vertexIndices);
        polygons.get(0).setNormalIndices(normalIndices);

        try {
            objWriter.recordPolygons(fileWriter, polygons);
            BufferedReader reader = new BufferedReader(new FileReader(NAME_FILE));
            BufferedReader readerNull = new BufferedReader(new FileReader(NAME_FILE));

            while (readerNull.readLine() != null) {
                line += reader.readLine();
            }
            reader.close();
            readerNull.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(strokeInfo, line);
    }

    @Test
    public void testRecordPolygonsWithoutNormals() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        ArrayList<Polygon> polygons = new ArrayList<>();
        String strokeInfo = "f 1/5 2/6 3/7 4/8";
        String line = "";

        ArrayList<Integer> vertexIndices = new ArrayList<>();
        vertexIndices.add(1);
        vertexIndices.add(2);
        vertexIndices.add(3);
        vertexIndices.add(4);

        ArrayList<Integer> textureVertexIndices = new ArrayList<>();
        textureVertexIndices.add(5);
        textureVertexIndices.add(6);
        textureVertexIndices.add(7);
        textureVertexIndices.add(8);

        polygons.add(new Polygon());
        polygons.get(0).setVertexIndices(vertexIndices);
        polygons.get(0).setTextureVertexIndices(textureVertexIndices);

        try {
            objWriter.recordPolygons(fileWriter, polygons);
            BufferedReader reader = new BufferedReader(new FileReader(NAME_FILE));
            BufferedReader readerNull = new BufferedReader(new FileReader(NAME_FILE));

            while (readerNull.readLine() != null) {
                line += reader.readLine();
            }
            reader.close();
            readerNull.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(strokeInfo, line);
    }

    @Test
    public void testRecordModel() {
        ObjWriter objWriter = new ObjWriter();
        FileWriter fileWriter = ObjWriter.fileCorrectness(NAME_FILE);
        Path fileName = Path.of("3DModelsForTest/WrapJaw.obj");

        try {
            String fileContent = Files.readString(fileName);
            Model model = ObjReader.read(fileContent);
            objWriter.recordModel(NAME_FILE, model);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void recordComment() {
    }

    @Test
    public void recordVertices() {
    }

    @Test
    public void recordTextureVertices() {
    }

    @Test
    public void recordNormals() {
    }

    @Test
    public void recordPolygons() {
    }
}
