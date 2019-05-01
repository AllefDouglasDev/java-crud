package br.com.mercado.models.bean;

import java.sql.Timestamp;
import java.util.List;

import br.com.mercado.database.DBSQLiteConnection;
import br.com.mercado.models.dao.CategoryDAO;

public class Category {

    private long id;

    private String name;

    private List<Product> products;

    private boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Category (String name) {
        this.name = name;
    }

    public Category (long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category (long id, String name, boolean isDeleted, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        if (null == products)
            products = CategoryDAO.getInstance(new DBSQLiteConnection()).products(id);

        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Category [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", products=");
        builder.append(products);
        builder.append(", isDeleted=");
        builder.append(isDeleted);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append(", updatedAt=");
        builder.append(updatedAt);
        builder.append("]");
        return builder.toString();
    }
}
