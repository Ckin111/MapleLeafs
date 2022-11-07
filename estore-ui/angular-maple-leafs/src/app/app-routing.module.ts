import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { AddJerseyComponent } from './add-jersey/add-jersey.component';
import { BrowseJerseyComponent } from './browse-jersey/browse-jersey.component';
import { ViewJerseyComponent } from './view-jersey/view-jersey.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  {path: 'view/:id', component: ViewJerseyComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'login', component: LoginComponent},
  {path: 'user', component: UserComponent},
  {path: 'browse', component: BrowseJerseyComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'add', component: AddJerseyComponent},
  {path: 'cart', component: ShoppingCartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
