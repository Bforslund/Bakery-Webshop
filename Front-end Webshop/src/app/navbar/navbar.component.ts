import { Component, OnInit } from '@angular/core';
import { UsersService } from '../shared/users.service';
import {User} from '../model/User';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  id:string;
  admin:boolean;
  user = new User(1, "Bea", "dummy data", "test1", 200, "1999", "kuk@live.se", "123","USER")
  constructor(private service: UsersService) { }
loggedIn:boolean;
  ngOnInit(): void {
    if(this.readLocalStorageValue() != null){
      this.loggedIn= true;
      console.log("logged innnn");
    }else{
      this.loggedIn = false;
    }

    this.id = localStorage.getItem('userId');
    this.service.getUserById(this.id)
    .subscribe((data)=>{
     this.user = <User>data;
     console.log(this.user);
     if(this.user.role === "ADMIN"){
      this.admin = true;
      
    }else{
      this.admin = false;
    }
    });
   


  }
  readLocalStorageValue() {
    return localStorage.getItem('userToken');
}

logout(){
  location.reload();
  localStorage.clear();
  this.service.logout();
}

}