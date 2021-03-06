import { Component, OnInit } from '@angular/core';
import { OrdersService } from '../shared/orders.service';
import {User} from '../model/User';
import {Order} from '../model/Order';
import {OrderItem} from '../model/OrderItem';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.scss']
})
export class ReceiptComponent implements OnInit {
order: Order = new Order(55, 3, "", "");
user: User = new User(1, "", "", "", 200, "", "", "", "USER");
orderItemsList: Array<OrderItem> = [];
id: number;
  constructor(private service: OrdersService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.service.getUserByOrderId(this.id)
    .subscribe((data)=>{
    
     this.user = <User>data;
    });
    this.service.getOrderById(this.id)
    .subscribe((data)=>{
      
     this.order = <Order>data;
    });
    this.service.getOrderItems(this.id)
    .subscribe((data)=>{
     this.orderItemsList = <Array<OrderItem>>data;
     console.log(this.orderItemsList);
     this.dataSource = this.orderItemsList;
    });
   
  }
 displayedColumns = [ 'item', 'quantity'];
  dataSource;
}
