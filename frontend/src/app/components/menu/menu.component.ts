import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(public loginService: LoginService, private route: Router) { }

  ngOnInit(): void {
  }

  handleLogout() {
    this.loginService.logout();
    this.route.navigate(['login']);
  }

}
