package org.example.Math;


import java.util.Objects;

public class Vector3f extends Vector {
    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", getX(), getY(), getZ());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3f other = (Vector3f) obj;
        return Math.abs(x - other.x) < EPSILON && Math.abs(y - other.y) < EPSILON && Math.abs(z - other.z) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
