package br.com.mercado.database.seeds;

import java.util.ArrayList;
import java.util.List;

public class SeederBuilder {

    private static List<Seeder> seeders;

    public static void seed() {
        add(new CategoriesTableSeeder());
        add(new ProductsTableSeeder());

        run();
    }

    private static void add(Seeder s) {
        if (null == seeders)
            seeders = new ArrayList<>();

        seeders.add(s);
    }

    private static void run() {
        if (null == seeders)
            seeders = new ArrayList<>();

        seeders.forEach(s -> {
            s.run();
            System.out.println("Seeder success -> " + s.getClass().getSimpleName());
        });
    }
}
