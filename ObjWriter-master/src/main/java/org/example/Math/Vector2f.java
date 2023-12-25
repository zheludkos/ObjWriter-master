package org.example.Math;

import java.util.Objects;

public class Vector2f extends Vector {
    private float x, y;

    public Vector2f(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getX(), getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector2f other = (Vector2f) obj;
        return Math.abs(x - other.x) < EPSILON && Math.abs(y - other.y) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
