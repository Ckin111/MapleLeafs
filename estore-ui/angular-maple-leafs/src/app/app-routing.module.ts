import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowseJerseyComponent } from './browse-jersey/browse-jersey.component';
import { ViewJerseyComponent } from './view-jersey/view-jersey.component';

const routes: Routes = [
  {path: 'view/:id', component: ViewJerseyComponent},
  {path: 'browse', component: BrowseJerseyComponent},
  {path: '', redirectTo: '/browse', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
