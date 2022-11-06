import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { UserService } from '../user.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  user: string = "admin";
  jerseys: Jersey[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getCart();
  }

  isNotEmpty(): boolean {
    return this.jerseys.length != 0;
  }

  getCart(): void {
    this.userService.getCart(this.user).subscribe(jerseys => this.jerseys = jerseys);
  }

  delete(jersey: Jersey): void {
    if(this.jerseys) {
      this.jerseys = this.jerseys.filter(j => j !== jersey);
      this.userService.removeFromCart(this.user, jersey).subscribe();
    }
  }

}
