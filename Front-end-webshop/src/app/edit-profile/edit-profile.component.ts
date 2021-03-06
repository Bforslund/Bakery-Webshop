import { Component, OnInit } from '@angular/core';
import { UsersService } from '../shared/users.service';
import {User} from '../model/User';
import { Router } from '@angular/router';
@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.scss']
})
export class EditProfileComponent implements OnInit {
  notification = null; 
  loggedIn:boolean;
  id:string;
  token:string;
  constructor(private service: UsersService, private router : Router) { }
user = new User(1, "", "", "", 0, "", "", "","")

ngOnInit(): void {
    this.service.getUser()
    .subscribe((data)=>{
      console.log(data);
     this.user = <User>data;
    });
  
  }
  update() {
    this.service.updateUser(this.user).subscribe(
      (res: any) => {
        this.showNotification();
      });
}

showNotification() {
      this.notification = { class: 'text-primary', message: 'updated!' };

}

updatePassword(data){
  this.user.password = data.password;
  this.service.updateUserPassword(this.user).subscribe(
    (res: any) => {
      this.showNotification();
    });
}
}
