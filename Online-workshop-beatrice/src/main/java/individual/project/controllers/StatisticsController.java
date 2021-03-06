package individual.project.controllers;
import individual.project.model.*;
import individual.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController {
    IOrdersRepository ordersRepository;

    public StatisticsController(IOrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public double getTotalRevenue(){
        try {
            double total = 0;
            List<Order> orderList = ordersRepository.getOrders();
            for (Order o : orderList) {
                total += o.getTotalPrice();
            }
            return total;
        }catch(Exception e){
            return 0;
        }
    }
    public int getTotalAmountOfCakesSold(){
        try{
        int total = 0;
        List<Order> orderList = ordersRepository.getOrders();
        for (Order o:orderList) {
            for(OrderItem oi:o.getOrderedItemsList()){
                Item i = oi.getItem();
                if(i.getType().equals(Item.TypeOfItem.CAKE)){
                    total += oi.getQuantity();
                }
            }

        }
        return total;
    }catch(Exception e){
        return 0;
    }
    }
    public int getTotalAmountOfCupcakesSold(){
        try{
            int total = 0;
            List<Order> orderList = ordersRepository.getOrders();
            for (Order o:orderList) {
                for(OrderItem oi:o.getOrderedItemsList()){
                    Item i = oi.getItem();
                    if(i.getType().equals(Item.TypeOfItem.CUPCAKE)){
                        total += oi.getQuantity();
                    }
                }

            }
            return total;
        }catch(Exception e){
            return 0;
        }
    }
    public int getTotalAmountOfCookiesSold(){
        try{
            int total = 0;
            List<Order> orderList = ordersRepository.getOrders();
            for (Order o:orderList) {
                for(OrderItem oi:o.getOrderedItemsList()){
                    Item i = oi.getItem();
                    if(i.getType().equals(Item.TypeOfItem.COOKIE)){
                        total += oi.getQuantity();
                    }
                }

            }
            return total;
        }catch(Exception e){
            return 0;
        }
    }
    public int getTotalAmountOfOtherSold(){
        try{
            int total = 0;
            List<Order> orderList = ordersRepository.getOrders();
            for (Order o:orderList) {
                for(OrderItem oi:o.getOrderedItemsList()){
                    Item i = oi.getItem();
                    if(i.getType().equals(Item.TypeOfItem.OTHER)){
                        total += oi.getQuantity();
                    }
                }

            }
            return total;
        }catch(Exception e){
            return 0;
        }
    }
    public List<StatisticsOrder> getOrderPerMonth() {
        List<StatisticsOrder> StatList = new ArrayList<>();

        try {
            List<Integer> allMonths = new ArrayList<>();
            List<Order> orderList = ordersRepository.getOrders();
            for (Order o : orderList) {
                LocalDate time = o.getTime();
                allMonths.add(time.getMonthValue());

            }


            StatisticsOrder jan = new StatisticsOrder("JANUARY", 0);
            StatisticsOrder feb = new StatisticsOrder("FEBRUARY", 0);
            StatisticsOrder mar = new StatisticsOrder("MARCH", 0);
            StatisticsOrder apr = new StatisticsOrder("APRIL", 0);
            StatisticsOrder may = new StatisticsOrder("MAY", 0);
            StatisticsOrder jun = new StatisticsOrder("JUNE", 0);
            StatisticsOrder jul = new StatisticsOrder("JULY", 0);
            StatisticsOrder aug = new StatisticsOrder("AUGUST", 0);
            StatisticsOrder sep = new StatisticsOrder("SEPTEMBER", 0);
            StatisticsOrder oct = new StatisticsOrder("OCTOBER", 0);
            StatisticsOrder nov = new StatisticsOrder("NOVEMBER", 0);
            StatisticsOrder dec = new StatisticsOrder("DECEMBER", 0);

            for (Integer m: allMonths) {
                switch (m) {
                    case 1:
                        int i = jan.getTotalOrders();
                        i++;
                        jan.setTotalOrders(i);
                        break;
                    case 2:
                        i = feb.getTotalOrders();
                        i++;
                        feb.setTotalOrders(i);
                        break;
                    case 3:
                        i = mar.getTotalOrders();
                        i++;
                        mar.setTotalOrders(i);
                        break;
                    case 4:
                        i = apr.getTotalOrders();
                        i++;
                        apr.setTotalOrders(i);
                        break;
                    case 5:
                        i = may.getTotalOrders();
                        i++;
                        may.setTotalOrders(i);
                        break;
                    case 6:
                        i = jun.getTotalOrders();
                        i++;
                        jun.setTotalOrders(i);
                        break;
                    case 7:
                        i = jul.getTotalOrders();
                        i++;
                        jul.setTotalOrders(i);
                        break;
                    case 8:
                        i = aug.getTotalOrders();
                        i++;
                        aug.setTotalOrders(i);
                        break;
                    case 9:
                        i = sep.getTotalOrders();
                        i++;
                        sep.setTotalOrders(i);
                        break;
                        case 10:
                        i = oct.getTotalOrders();
                        i++;
                        oct.setTotalOrders(i);
                        break;
                    case 11:
                        i = nov.getTotalOrders();
                        i++;
                        nov.setTotalOrders(i);
                        break;
                    case 12:
                        i = dec.getTotalOrders();
                        i++;
                        dec.setTotalOrders(i);
                        break;
                    default:
                        i = dec.getTotalOrders();
                        i++;
                        dec.setTotalOrders(i);
                        break;
                }
            }

            StatList.add(jan);
            StatList.add(feb);
            StatList.add(mar);
            StatList.add(apr);
            StatList.add(may);
            StatList.add(jun);
            StatList.add(jul);
            StatList.add(aug);
            StatList.add(sep);
            StatList.add(oct);
            StatList.add(nov);
            StatList.add(dec);
            return StatList;

        } catch (Exception e) {
            return StatList;
        }
    }
}
