package org.example.configuration;

import java.util.Objects;

public class Point {
    private int x;
    private int y;
    public Point() {}
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Проверка на идентичность
        if (obj == null || getClass() != obj.getClass()) return false; // Проверка на null и совпадение классов
        Point point = (Point) obj; // Приведение типа
        return x == point.x && y == point.y; // Сравнение по координатам
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); // Генерация хэш-кода на основе координат
    }
}
