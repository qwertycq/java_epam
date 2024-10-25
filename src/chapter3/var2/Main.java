package chapter3.var2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Quadrangle> quadrilaterals = new ArrayList<>();
        quadrilaterals.add(new Quadrangle(5,5,5,5,90,90,90,90));
        quadrilaterals.add(new Quadrangle(6,6,6,6,120,60,120,60));
        quadrilaterals.add(new Quadrangle(4,7,4,7,90,90,90,90));
        quadrilaterals.add(new Quadrangle(3,4,5,6,85,95,100,80));

        Map<String, List<Quadrangle>> grouped = new HashMap<>();
        for (Quadrangle q : quadrilaterals) {
            String type = q.getType();
            grouped.putIfAbsent(type, new ArrayList<>());
            grouped.get(type).add(q);
        }

        for (Map.Entry<String, List<Quadrangle>> entry : grouped.entrySet()) {
            String type = entry.getKey();
            List<Quadrangle> list = entry.getValue();
            System.out.println(type + ":");

            Quadrangle maxAreaQuad = Collections.max(list, Comparator.comparingDouble(Quadrangle::getArea));
            Quadrangle minAreaQuad = Collections.min(list, Comparator.comparingDouble(Quadrangle::getArea));

            Quadrangle maxPerimeterQuad = Collections.max(list, Comparator.comparingDouble(Quadrangle::getPerimeter));
            Quadrangle minPerimeterQuad = Collections.min(list, Comparator.comparingDouble(Quadrangle::getPerimeter));

            System.out.println("  Max area: " + maxAreaQuad);
            System.out.println("  Min area: " + minAreaQuad);
            System.out.println("  Max perimeter: " + maxPerimeterQuad);
            System.out.println("  Min perimeter: " + minPerimeterQuad);
        }


    }
}
