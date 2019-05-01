package br.com.mercado.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.mercado.database.interfaces.Database;
import br.com.mercado.models.bean.Category;
import br.com.mercado.models.bean.Product;
import br.com.mercado.models.interfaces.IProductDAO;

public class ProductDAO implements IProductDAO {

    private static ProductDAO instance;

    private Connection conn;

    public static ProductDAO getInstance(Database database) {
        if (instance == null) {
            synchronized (CategoryDAO.class) {
                if (instance == null) {
                    instance = new ProductDAO(database);
                }
            }
        }

        return instance;
    }

    private ProductDAO(Database database) {
        conn = database.getConnection();
    }

    /**
     * Cria um novo produto
     *
     * @param product
     */
    @Override
    public void store(Product product) {
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO products (category_id, name, price) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, product.getCategoryId());
            stmt.setString(2, product.getName());
            stmt.setFloat(3, product.getPrice());
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Product> findAll() {
        PreparedStatement stmt = null;
        ResultSet rs;

        List<Product> products = new ArrayList<>();

        try {
            String query = "SELECT * FROM products WHERE deleted = 0";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getLong("category_id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("deleted") == 1,
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                products.add(product);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }

    @Override
    public Product findOne(long id) {
        PreparedStatement stmt = null;
        ResultSet rs;

        Product product = null;

        try {
            String query = "SELECT * FROM products WHERE id = ? AND deleted = 0";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getLong("category_id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("deleted") == 1,
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return product;
    }

    @Override
    public void update(long id, Product product) {
        PreparedStatement stmt = null;

        try {
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
            String query = "UPDATE products "
                    + " SET category_id = ?, name = ?, price = ?, updated_at = ? "
                    + " WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, product.getCategoryId());
            stmt.setString(2, product.getName());
            stmt.setFloat(3, product.getPrice());
            stmt.setTimestamp(4, updatedAt);
            stmt.setLong(5, id);
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(long id) {
        PreparedStatement stmt = null;

        try {
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
            String query = "UPDATE products SET deleted = 1, updated_at = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setTimestamp(1, updatedAt);
            stmt.setLong(2, id);
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Category category(long id) {
        PreparedStatement stmt = null;
        ResultSet rs;

        Category category = null;

        try {
            String query = "SELECT c.* "
                    + " FROM products AS p "
                    + " JOIN categories AS c "
                    + " ON p.category_id = c.id "
                    + " WHERE p.id = ? AND p.deleted = 0";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("deleted") == 1,
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return category;
    }
}
