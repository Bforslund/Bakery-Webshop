package individual.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "individual_items")
public class Item {



    public enum TypeOfItem {
        CAKE, CUPCAKE, COOKIE, OTHER
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        if (sellingPrice < buyingPrice || sellingPrice < 0 || sellingPrice == 0)
        {
            throw new IllegalArgumentException();
        }
        this.sellingPrice = sellingPrice;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        if (buyingPrice < 0 || buyingPrice == 0)
        {
            throw new IllegalArgumentException();
        }
        this.buyingPrice = buyingPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        if (ingredients == null || ingredients.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.ingredients = ingredients;
    }
    public TypeOfItem getType() {
        return type;
    }

    public void setType(TypeOfItem type) {
        if (type == null)
        {
            throw new IllegalArgumentException();
        }
        this.type = type;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "selling_price")
    private int sellingPrice;
    @Column(name = "buying_price")
    private int buyingPrice;
    @Column(name = "ingredients")
    private String ingredients;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeOfItem type;


    public Item(String name, int sellingPrice, int buyingPrice, String ingredients, TypeOfItem type) {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
        this.ingredients = ingredients;
        this.type = type;

    }

    public Item(int id, String name, int sellingPrice, int buyingPrice, String ingredients, TypeOfItem type) {
        this.id = id;
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
        this.ingredients = ingredients;
        this.type = type;
    }

    public Item() {
    }


}
