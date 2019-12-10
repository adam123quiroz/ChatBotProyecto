package edu.com.chatbotsoftI.invoice;

public class Article{
    public int SNO;
    public String description;
    public int quantity;
    public double unitPrice;
    public Article(int SNO, String description, int quantity, double unitPrice)
    {
        this.SNO = SNO;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}