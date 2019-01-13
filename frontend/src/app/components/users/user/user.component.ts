import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User = new User();
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getUser(this.route.params['value'].username);
  }

  getUser(userId: number): void {
    this.userService.getUser(userId).subscribe(resp => {
      this.user = resp;
    });
  }
}
