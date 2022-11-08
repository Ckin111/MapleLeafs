import { Component, OnInit } from '@angular/core';
import { Jersey } from '../jersey';
import { UserService } from '../user.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  username: string = "";
  jerseys: Jersey[] = [];

  constructor(private userService: UserService, private location: Location,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.username = String(this.route.snapshot.paramMap.get('Name'));
    this.getCart();
  }

  isNotEmpty(): boolean {
    return this.jerseys.length != 0;
  }

  getCart(): void {
    console.log("Username: "+this.username);
    this.userService.getCart(this.username).subscribe(jerseys => this.jerseys = jerseys);
  }

  delete(jersey: Jersey): void {
    if(this.jerseys) {
      this.jerseys = this.jerseys.filter(j => j !== jersey);
      this.userService.removeFromCart(this.username, jersey).subscribe();
    }
  }

  checkout(): void {
    this.userService.clearCart(this.username).subscribe();
    window.location.reload();
  }

  back(): void {
    this.location.back();
  }

}
