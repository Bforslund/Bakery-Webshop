package individual.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "individual_orders")
public class Order {
    public enum orderStatus {
SHIPPED, PENDING
    }

    public Order( int userId, String address, String customerName, double totalPrice, List<OrderItem> orderItems) {

        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = orderStatus.PENDING;
        this.customerName = customerName;
        this.address = address;
        this.time = LocalDate.now();
        orderedItemsList  = orderItems;
    }

    public Order(int orderNumber, double totalPrice, int userId, String address, String customerName) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.address = address;
        this.customerName = customerName;
        this.time = LocalDate.now();
        this.status = orderStatus.PENDING;
        orderedItemsList  = new ArrayList<>();
    }

    public Order() {
        orderedItemsList  = new ArrayList<>();
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "orderNumber")
    private int orderNumber;
    @Column(name = "totalPrice")
    private double totalPrice;
    @Column(name = "userId")
    private int userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private orderStatus status;
    @Column(name = "time")
    private LocalDate time;
    @Column(name = "address")
    private String address;
    @Column(name = "customerName")
    private String customerName;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderedItemsList;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public void addItemToList(OrderItem i) {

        i.setOrder(this);
        getOrderedItemsList().add(i);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
//    public double getTotalPrice() {
//        CalculateTotalPrice();
//        return totalPrice;
//    }
//
//    public void CalculateTotalPrice() {
//        double price = 0;
//        for (OrderItem order:orderedItemsList) {
//            price += order.getItem().getSellingPrice();
//            price *= order.getQuantity();
//        }
//        this.totalPrice = price;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public orderStatus getStatus() {
        return status;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public List<OrderItem> getOrderedItemsList() {
        return orderedItemsList;
    }

    public void setOrderedItemsList(List<OrderItem> orderedItemsList) {
        this.orderedItemsList = orderedItemsList;
    }
}
