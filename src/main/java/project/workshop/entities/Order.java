package project.workshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import project.workshop.enums.OrderStatus;
import project.workshop.requestPayLoad.OrderRequestPayLoad;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime moment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    private Integer orderStatus;

    @OneToMany(mappedBy = "id.order")
    Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
        super();
    }

    //Excluir no final este construtor, pós está sendo usado apenas no seeding de test.
    public Order(Integer id, LocalDateTime moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = LocalDateTime.now();
        setOrderStatus(orderStatus);
        this.client = client;
    }

    //Construtor do DTO, passando User também pós na classe de service iremos buscar o usuário responsável pelo pedido através do ID do usuário.
    public Order(OrderRequestPayLoad payLoad, User user) {
        this.id = payLoad.id();
        this.moment = LocalDateTime.now();
        setOrderStatus(OrderStatus.WAITING_PAYMENT);
        this.client = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @JsonIgnore
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status.getCode();
    }

    //Responsável pela soma total do pedido.
    public double getTotal() {
        double sum = 0.0;
        for (OrderItem orderItem : items) {
            sum += orderItem.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
