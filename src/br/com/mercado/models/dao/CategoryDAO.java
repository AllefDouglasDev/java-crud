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
import br.com.mercado.models.interfaces.ICategoryDAO;

public class CategoryDAO implements ICategoryDAO {

    private static CategoryDAO instance;

    private Connection conn;

    public static CategoryDAO getInstance(Database database) {
        if (instance == null) {
            synchronized (CategoryDAO.class) {
                if (instance == null) {
                    instance = new CategoryDAO(database);
                }
            }
        }

        return instance;
    }

    private CategoryDAO(Database database) {
        conn = database.getConnection();
    }

    /**
     * Cria uma nova categoria
     *
     * @param category
     */
    @Override
    public void store (Category category) {
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO categories (name) VALUES (?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, category.getName());
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

    /**
     * Busca todas as categorias cadastradas
     */
    @Override
    public List<Category> findAll() {
        PreparedStatement stmt = null;
        ResultSet rs;

        List<Category> categories = new ArrayList<>();

        try {
            String query = "SELECT * FROM categories WHERE deleted = 0";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("deleted") == 1,
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                categories.add(category);
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

        return categories;
    }

    /**
     * Busca uma categoria pelo seu ID
     *
     * @param id
     */
    @Override
    public Category findOne(long id) {
        PreparedStatement stmt = null;
        ResultSet rs;

        Category category = null;

        try {
            String query = "SELECT * FROM categories WHERE id = ? AND deleted = 0";
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

    /**
     * Altera uma categoria a partir do seu ID
     *
     * @param id, category
     */
    @Override
    public void update(long id, Category category) {
        PreparedStatement stmt = null;

        try {
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
            String query = "UPDATE categories SET name = ?, updated_at = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, category.getName());
            stmt.setTimestamp(2, updatedAt);
            stmt.setLong(3, id);
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

    /**
     * Deleta uma categoria a partir do seu ID
     * Faz deleção lógica no campo deleted, sendo 1 para deletado e 0 para não deletado
     *
     * @param id
     */
    @Override
    public void delete(long id) {
        PreparedStatement stmt = null;

        try {
            Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
            String query = "UPDATE categories SET deleted = 1, updated_at = ? WHERE id = ?";
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
    public List<Product> products(long id) {
        PreparedStatement stmt = null;
        ResultSet rs;

        List<Product> products = new ArrayList<>();

        try {
            String query = "SELECT p.* "
                    + " FROM categories AS c "
                    + " JOIN products AS p "
                    + " ON c.id = p.category_id"
                    + " WHERE c.id = ? AND c.deleted = 0 AND p.deleted = 0";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);
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
}
