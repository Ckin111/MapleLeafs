import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { AddJerseyComponent } from './add-jersey/add-jersey.component';
import { BrowseJerseyComponent } from './browse-jersey/browse-jersey.component';
import { ViewJerseyComponent } from './view-jersey/view-jersey.component';

const routes: Routes = [
  {path: 'view/:id', component: ViewJerseyComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'user', component: UserComponent},
  {path: 'browse', component: BrowseJerseyComponent},
  {path: '', redirectTo: '/browse', pathMatch: 'full'},
  {path: 'add', component: AddJerseyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
