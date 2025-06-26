package org.claumann;

import java.util.Objects;

public class Product {

    private String code;
    private String nome;
    private int quantity;

    public Product(String code, String nome, int quantity) {
        this.code = code;
        this.nome = nome;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code) && Objects.equals(nome, product.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, nome);
    }

    @Override
    public String toString() {
        return String.format("{ \"code\": \"%s\", \"nome\": \"%s\", \"quantity\": %s] }", code, nome, quantity);
    }

}
